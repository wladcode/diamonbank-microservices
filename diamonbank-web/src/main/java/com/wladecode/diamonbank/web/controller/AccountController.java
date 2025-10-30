package com.wladecode.diamonbank.web.controller;

import com.wladecode.diamonbank.web.response.AccounResponse;
import com.wladecode.diamonbank.web.response.ResponseDTO;
import com.wladecode.diamonbank.web.utils.UtilJwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class AccountController {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    @Value("${api-gateway.url}")
    private String apiGatewayUrl;

    public AccountController(OAuth2AuthorizedClientService authorizedClientService, RestTemplate restTemplate, WebClient webClient) {
        this.authorizedClientService = authorizedClientService;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @GetMapping("/accounts")
    public String showAccount(Model model, @AuthenticationPrincipal OAuth2User principal, Authentication authentication) {
        String jwtToken = UtilJwt.getJwtToken(authentication, authorizedClientService);

        long userId = 1;
        if (model.containsAttribute("userInfo")) {
            Map<String, Object> userInfo = (Map<String, Object>) model.getAttribute("userInfo");
            if (userInfo != null && userInfo.containsKey("id")) {
                userId = (long) userInfo.get("id");
            }
        }


        List<AccounResponse> accounts = getAccountsFromRestTemplate(jwtToken, userId); //getAccountsFromWebClient();
        Map<String, Object> accountSummary = calculateAccountSummary(accounts);

        model.addAttribute("accounts", accounts);
        model.addAttribute("accountSummary", accountSummary);
        model.addAttribute("currentDate", LocalDateTime.now());
        model.addAttribute("fullName", principal.getAttribute("name"));


        return "accounts";
    }

    private Map<String, Object> calculateAccountSummary(List<AccounResponse> accounts) {
        Map<String, Object> summary = new HashMap<>();

        // Calculate total balance
        BigDecimal totalBalance = accounts.stream().map(AccounResponse::balance).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        // Count active accounts (assuming all accounts with balance >= 0 are active)
        long activeAccounts = accounts.stream().filter(acc -> acc.balance() != null && acc.balance().compareTo(BigDecimal.ZERO) >= 0).count();

        summary.put("totalBalance", totalBalance);
        summary.put("activeAccounts", activeAccounts);
        summary.put("totalAccounts", accounts.size());

        return summary;
    }

    private List<AccounResponse> getDummyAccounts() {

        AccounResponse accountResponse1 = new AccounResponse(1, 1, "123456789", "Savings", new BigDecimal(1000), LocalDateTime.now(), "123 Main St");

        AccounResponse accountResponse2 = new AccounResponse(1, 1, "123456789", "Checking", new BigDecimal(1000), LocalDateTime.now(), "123 Main St");

        return List.of(accountResponse1, accountResponse2);
    }

    public List<AccounResponse> getAccountsFromRestTemplate(String jwtToken, long idUser) {
        // 1. Define the base URL
        String url = apiGatewayUrl + "/accounts/" + idUser;

        // 3. Setup headers and request entity (remains the same)
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // 4. Execute the exchange using the new URL
        ResponseEntity<ResponseDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<ResponseDTO>() {
        });

        // 5. Return the data
        return (List<AccounResponse>) responseEntity.getBody().getData();

    }

    private List<AccounResponse> getAccountsFromWebClient() {
        String url = "http://localhost:8072/api/accounts";
        return webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<List<AccounResponse>>() {
        }).block();
    }

}
