package com.diamoncode.diamonbank.accounts.adapter.out.persistence;

import com.diamondcode.common.adapter.in.web.exception.CustomFoundException;
import com.diamondcode.common.adapter.in.web.exception.CustomNotFoundException;
import com.diamoncode.diamonbank.accounts.adapter.in.web.feing.cards.CardsFeingClient;
import com.diamoncode.diamonbank.accounts.adapter.in.web.feing.loans.LoansFeingClient;
import com.diamoncode.diamonbank.accounts.adapter.in.web.feing.product.ProductClient;
import com.diamoncode.diamonbank.accounts.adapter.out.persistence.mapper.AccountMapper;
import com.diamoncode.diamonbank.accounts.adapter.out.persistence.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.adapter.out.persistence.repository.AccountsRepository;
import com.diamoncode.diamonbank.accounts.aplication.port.out.AccountPort;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.*;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerResponse;
import com.diamoncode.diamonbank.accounts.domain.Account;
import com.diamondcode.common.adapter.in.messaging.AccountMessageDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements AccountPort {

    private final AccountsRepository accountsRepository;
    private final CustomerAdapter customerAdapter;
    private final LoansFeingClient loansFeingClient;
    private final CardsFeingClient cardsFeingClient;
    private final ProductClient productClient;
    private final AccountMapper accountMapper;
    private final StreamBridge streamBridge;

    @Override
    public List<AccountDto> getAccountsByUser(Long userId) {
        List<JpaEntityAccount> accounts = accountsRepository.findByCustomerId(userId);
        return accountMapper.mapToDomain(accounts);
    }

    @Override
    public ConsolidatePositionDto getConsolidatePosition(Long accountId, String correlationId) {
        log.debug("correlationId: {},  time start: {}", correlationId, System.currentTimeMillis());

        CustomerDto customerDto = new CustomerDto(accountId);
        CompletableFuture<List<JpaEntityAccount>> futureAccounts = CompletableFuture.supplyAsync(() -> accountsRepository.findByCustomerId(accountId));
        CompletableFuture<List<LoansDto>> futureLoans = CompletableFuture.supplyAsync(() -> loansFeingClient.getLoans(customerDto));
        CompletableFuture<List<CardsDto>> futureCards = CompletableFuture.supplyAsync(() -> cardsFeingClient.getCardDetails(customerDto));
        CompletableFuture<ProductDto> futureProduct = CompletableFuture.supplyAsync(() -> productClient.getProductById(accountId));

        ConsolidatePositionDto consolidatePositionDto = CompletableFuture.allOf(futureAccounts, futureLoans, futureCards, futureProduct)
                .thenApply(v -> ConsolidatePositionDto.builder()
                        .accounts(accountMapper.mapToDomain(futureAccounts.join()))
                        .loans(futureLoans.join())
                        .cards(futureCards.join())
                        .product(futureProduct.join())
                        .build()).join();

        log.debug("correlationId: {}, time end:{}", correlationId, System.currentTimeMillis());

        return consolidatePositionDto;
    }


    @Override
    public Account loadAccount(Long accountId, LocalDateTime now) {
        return null;
    }

    @Override
    public AccountDto getAccountByUser(long idAccount) {
        Optional<JpaEntityAccount> account = accountsRepository.findByAccountId(idAccount);

        if (account.isPresent()) {
            log.error("The account for found");
            return accountMapper.mapToDomain(account.get());
        }

        throw new CustomNotFoundException(String.format("Account with id %s not found", idAccount));

    }

    @Transactional
    public long createAccount(AccountDto accountDto, long customerId) {
        CustomerResponse customer = customerAdapter.findById(customerId);

        if (accountsRepository.findByAccountNumber(accountDto.getNumber()).isPresent()) {
            throw new CustomFoundException(
                    String.format("There is an account with the number %s ", accountDto.getNumber())
            );
        }

        JpaEntityAccount jpaEntityAccount = accountMapper.mapToJpaEntity(accountDto);
        jpaEntityAccount.setCustomerId(customer.id());

        accountsRepository.save(jpaEntityAccount);
        log.info("Account created successfully with id: {}", jpaEntityAccount.getAccountId());

        sendCommunication(accountDto, customer);
        return jpaEntityAccount.getAccountId();

    }

    public Page<AccountDto> findAllByIdUser(long customerId, Pageable pegeable) {

        int pageNumber = 0;
        int pageSize = 10;

        if (pegeable.getPageNumber() >= 0) {
            pageNumber = pegeable.getPageNumber();
        }
        if (pegeable.getPageSize() > 0 && pegeable.getPageSize() <= 20) {
            pageSize = pegeable.getPageSize();
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "accountNumber");
        if (!pegeable.getSort().isEmpty()) {
            sort = pegeable.getSort();
            Sort.Order order = sort.toList().get(0);
            String property = order.getProperty();
            sort = Sort.by(order.getDirection(), accountMapper.mapPropertyFromDto(property));
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<JpaEntityAccount> accountPageList = accountsRepository.findByCustomerId(customerId, pageRequest);
        if (accountPageList.isEmpty()) {
            log.error("No accounts found for user with id: {}", customerId);
            throw new CustomNotFoundException(String.format("No accounts found for user with id %s", customerId));
        }

        return accountPageList.map(accountMapper::mapToDomain);

    }

    private void sendCommunication(AccountDto accountDto, CustomerResponse customerResponse) {
        try {
            var accountMsgDto = new AccountMessageDto(
                    accountDto.getId(),
                    customerResponse.name(),
                    customerResponse.email(),
                    customerResponse.phone()
            );

            log.debug("Sending communication: {}", accountMsgDto);
            var result = streamBridge.send("sendCommunication-out-0", accountMsgDto);
            log.debug("Is the communication request successfully processed?: {}", result);
        } catch (MessageHandlingException e) {
            log.error("Error sending notification {} ", e.getMessage());
        }
    }

}
