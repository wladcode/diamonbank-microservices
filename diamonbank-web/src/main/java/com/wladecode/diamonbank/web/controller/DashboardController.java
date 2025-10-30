package com.wladecode.diamonbank.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wladecode.diamonbank.web.response.ConsolidatePositionResponse;
import com.wladecode.diamonbank.web.response.ResponseDTO;
import com.wladecode.diamonbank.web.utils.UtilJwt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
class CustomerRequest{
    private String name;
    private String email;

}


@Slf4j
@Controller
public class DashboardController {
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    @Value("${api-gateway.url}")
    private String apiGatewayUrl;

    public DashboardController(OAuth2AuthorizedClientService authorizedClientService, RestTemplate restTemplate, WebClient webClient) {
        this.authorizedClientService = authorizedClientService;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal OidcUser oidcUser, Authentication authentication) {
        String jwtToken = UtilJwt.getJwtToken(authentication, authorizedClientService);

        Map<String, Object> userInfo = getUserInfo(oidcUser, authentication);
        ConsolidatePositionResponse consolidatePositionResponse = getPositionConsolidated(jwtToken, (Long) userInfo.get("id"));

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("consolidatePositionResponse", consolidatePositionResponse);
        model.addAttribute("currentDate", LocalDateTime.now());
        model.addAttribute("fullName", userInfo.get("name"));
        return "dashboard";
    }


    private Map<String, Object> getUserInfo(OAuth2User oAuth2User, Authentication authentication) {
        String tokenValue = null;
        try {
            String jwtToken = UtilJwt.getJwtToken(authentication, authorizedClientService);
            String subject = UtilJwt.getSubject(oAuth2User);
            String name = oAuth2User.getAttribute("name");
            String email = oAuth2User.getAttribute("email");

            if (name == null || email == null) {
                throw new IllegalStateException("Required user attributes (name, email) are missing");
            }

            // 3. Prepare request
            CustomerRequest customerRequest = new CustomerRequest(name, email);
            String url = apiGatewayUrl + "/customer/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(jwtToken);
            headers.set("X-Auth-Request-User-Email", email);
            headers.set("X-Auth-Request-Subject", subject != null ? subject : "");

            HttpEntity<CustomerRequest> requestEntity = new HttpEntity<>(customerRequest, headers);

            //ResponseEntity<ResponseDTO> responseEntity = restTemplate.postForEntity(url, requestEntity, ResponseDTO.class);
            ResponseEntity<ResponseDTO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseDTO.class);

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() == null || responseEntity.getBody().getData() == null) {
                throw new IllegalStateException("Empty response body from customer service");
            }

            return (Map<String, Object>) responseEntity.getBody().getData();

        } catch (Exception e) {
            log.error("\n=== ERROR DETAILS ===");
            log.error("Error in getIdUser: " + e.getClass().getName() + ": " + e.getMessage());
            if (e.getCause() != null) {
                log.error("Root cause: " + e.getCause().getClass().getName() + ": " + e.getCause().getMessage());
            }
            log.error("Token being used: " + (tokenValue != null ? tokenValue.substring(0, Math.min(50, tokenValue.length())) + "..." : "null"));
            e.printStackTrace();
            throw new RuntimeException("Failed to get user ID: " + e.getMessage(), e);
        }
    }


    private ConsolidatePositionResponse getPositionConsolidated(String jwtToken, long idUser) {
        try {

            String url = apiGatewayUrl+ "/accounts/consolidate/"+idUser;
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(jwtToken);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<ResponseDTO> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            requestEntity,
                            new ParameterizedTypeReference<ResponseDTO>() {
            });

            Object data = responseEntity.getBody().getData();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(data, ConsolidatePositionResponse.class);

        } catch (Exception e) {
            log.error("\n=== ERROR DETAILS ===");
            e.printStackTrace();
            throw new RuntimeException("Failed to get consolidate position: " + e.getMessage(), e);
        }
    }




}
