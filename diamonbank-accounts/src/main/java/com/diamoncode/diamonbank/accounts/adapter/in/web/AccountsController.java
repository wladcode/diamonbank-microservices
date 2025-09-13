package com.diamoncode.diamonbank.accounts.adapter.in.web;

import com.diamoncode.diamonbank.accounts.adapter.in.web.config.AccountsServiceConfig;
import com.diamoncode.diamonbank.accounts.adapter.in.web.util.ResponseUtil;
import com.diamoncode.diamonbank.accounts.adapter.out.persistence.AccountPersistenceAdapter;
import com.diamoncode.diamonbank.accounts.aplication.port.in.AccountsUseCase;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.PropertiesDto;
//import com.diamoncode.i18n.client.I18nFactory;
import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(GlobalController.ACCOUNTS_REQUEST_MAPPING)
@RequiredArgsConstructor
public class AccountsController {
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    private final AccountsUseCase accountsUseCase;

    private final AccountsServiceConfig accountsServiceConfig;
    private final AccountPersistenceAdapter accountsAdapter;


    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody AccountDto accountDto, HttpServletRequest request) {
        logger.info("Request {}", request.getRequestURL());
        String idUser = request.getHeader("idUser");
        long idUserLong = idUser == null ? 0 : Long.parseLong(idUser);

        long idAccount = accountsAdapter.createAccount(accountDto, idUserLong);

        return ResponseUtil.buildResponseCreate(
                "Account created successfully",
                GlobalController.ACCOUNTS_REQUEST_MAPPING + GlobalController.ACCOUNTS_REQUEST_MAPPING_GET_BY_ID,
                Map.of("idAccount", idAccount));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<ResponseDTO> getAllAccounts(@PathVariable("idUser") long idUser, Pageable pegeable) {
        Page<AccountDto> accounts = accountsAdapter.findAllByIdUser(idUser, pegeable);
        logger.info("accounts data ", accounts.toString());
        return ResponseUtil.buildResponseLoad("Accounts with pagination loaded", accounts);
    }

    @GetMapping(GlobalController.ACCOUNTS_REQUEST_MAPPING_GET_BY_ID)
    public ResponseEntity<ResponseDTO> getAccountDetails(
            @PathVariable("idAccount") long idAccount,
            @AuthenticationPrincipal Jwt jwt) {

        // Example of accessing JWT claims
        if (jwt != null) {
            String subject = jwt.getSubject();
            String email = jwt.getClaim("email");
            logger.info("JWT Subject: {}", subject);
            logger.info("JWT Email: {}", email);
            logger.info("JWT Claims: {}", jwt.getClaims());
        }

        AccountDto accountDto = accountsUseCase.findById(idAccount);
        logger.info("account data: {}", accountDto);

        
        return ResponseUtil.buildResponseLoad("", accountDto);
    }


    @GetMapping("/getHost")
    public ResponseEntity<ResponseDTO> getHost() throws JsonProcessingException {

        Optional<String> podName = Optional.ofNullable(System.getenv("HOSTNAME"));
        Map<String, String> variables = System.getenv();

        String greeting = "Welcome to K8S cluster from " + variables;
        logger.info(greeting);

        return ResponseUtil.buildResponseLoad("", greeting);

    }

    @PostAuthorize("returnObject.body.data.msg == 'Hello World'")
    @GetMapping("/properties")
    public ResponseEntity<ResponseDTO> getProperties() throws JsonProcessingException {

        PropertiesDto properties = new PropertiesDto(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(), accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        logger.info("Properties found ", properties);

        return ResponseUtil.buildResponseLoad("", properties);

    }

/*
    @GetMapping("/testI18n/{withDefault}")
    public ResponseEntity<ResponseDTO> testI18n(@PathVariable("withDefault") boolean withDefault) {
        String headerName = null;
        if (withDefault) {
            headerName = I18nFactory.getInstance().getMessage("header.name", "es", "Valor por defecto");
        } else {
            headerName = I18nFactory.getInstance().getMessage("header.name", "es");
        }

        logger.info("headerName value ", headerName);


        ResponseEntity<ResponseDTO> responseDTO = ResponseUtil.buildResponseLoad("Respuesta obtenida desde I18N", headerName);


        try {
            AuditorFactory.send(responseDTO.toString());
        } catch (AuditException e) {
            logger.error("error sending data to rabbit");
        }

        return responseDTO;

    }*/


}
