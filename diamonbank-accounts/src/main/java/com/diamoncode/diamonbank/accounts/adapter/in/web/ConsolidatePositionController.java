package com.diamoncode.diamonbank.accounts.adapter.in.web;

import com.diamoncode.diamonbank.accounts.adapter.in.web.util.ResponseUtil;
import com.diamoncode.diamonbank.accounts.aplication.port.in.GetConsolidatePositionUseCase;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ConsolidatePositionDto;
import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(GlobalController.ACCOUNTS_REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class ConsolidatePositionController {

    private final GetConsolidatePositionUseCase getConsolidatePosition;

    @GetMapping("/consolidate/{customerId}")
    //@Retry(name = "retryCosolidation", fallbackMethod = "fallbackForRetryConsolidate")
    @RateLimiter(name = "rateLimiterConsolidate", fallbackMethod = "fallbackForRateLimiterConsolidate")
    @Timed(value = "getPositionConsolidate.time", description = "Time taked to return consolidate position")
    public ResponseEntity<ResponseDTO> getPositionConsolidate(@PathVariable("customerId") Long customerId) {

        ConsolidatePositionDto consolidatePositionDto = getConsolidatePosition.getConsolidatePosition(customerId);

        /*
        try {
            AuditorFactory.send(consolidatePositionDto.toString());
        } catch (AuditException e) {
            logger.error("error sending data to rabbit");
        }
        */

        return ResponseUtil.buildResponseLoad("Consolidate Position loaded successfully", consolidatePositionDto);
    }

    public  ResponseEntity<ResponseDTO> fallbackForRetryConsolidate(Long customerId, Throwable throwable) {
        log.error("fallback rate r ");
        throwable.printStackTrace();
        return ResponseUtil.buildResponseLoad("response from fallbackForRetryConsolidate", new ConsolidatePositionDto(null, null, null, null));

    }

    public  ResponseEntity<ResponseDTO> fallbackForRateLimiterConsolidate(Long customerId, Throwable throwable) {

        log.error("fallback rate limmiter ");

        throwable.printStackTrace();
        return ResponseUtil.buildResponseLoad("response from fallbackForRateLimiterConsolidate", new ConsolidatePositionDto(Arrays.asList(), Arrays.asList(), Arrays.asList(), null));

    }

}
