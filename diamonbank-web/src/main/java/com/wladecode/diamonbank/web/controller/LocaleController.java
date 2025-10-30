package com.wladecode.diamonbank.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocaleController {

    @GetMapping("/locale")
    public String locale(HttpServletRequest request) {
        String ultimaUrl = request.getHeader("referer"); // referer retorna la referencia de la ultima pagina

        return "redirect:".concat(ultimaUrl);

    }

}