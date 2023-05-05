package com.diamoncode.diamonbank.accounts.adapter.in.web;

import com.diamoncode.diamonbank.accounts.aplication.port.in.GetConsolidatePositionUseCase;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ConsolidatePositionDto;
import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import com.diamondcode.common.adapter.in.web.model.WebAdapterResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(GlobalController.ACCOUNTS_REQUEST_MAPPING)
@RequiredArgsConstructor
public class ConsolidatePositionController extends WebAdapterResponse {

    private final GetConsolidatePositionUseCase getConsolidatePosition;

    @GetMapping("/consolidate/{customerId}")
    //@Retry(name = "retryCosolidation", fallbackMethod = "fallbackForRetryConsolidate")
    @RateLimiter(name = "rateLimiterConsolidate", fallbackMethod = "fallbackForRateLimiterConsolidate")
    public ResponseDTO getCustomerDetails(@PathVariable("customerId") Long customerId) {

        ConsolidatePositionDto consolidatePositionDto = getConsolidatePosition.getConsolidatePosition(customerId);

        return getResponse("", consolidatePositionDto);
    }

    public ResponseDTO fallbackForRetryConsolidate(Long customerId, Throwable throwable) {

        throwable.printStackTrace();
        return getResponse("response from fallbackForRetryConsolidate", new ConsolidatePositionDto(null, null, null));

    }

    public ResponseDTO fallbackForRateLimiterConsolidate(Long customerId, Throwable throwable) {

        throwable.printStackTrace();
        return getResponse("response from fallbackForRateLimiterConsolidate", new ConsolidatePositionDto(Arrays.asList(), Arrays.asList(), Arrays.asList()));

    }

}
