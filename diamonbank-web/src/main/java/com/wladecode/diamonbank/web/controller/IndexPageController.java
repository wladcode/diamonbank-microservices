package com.wladecode.diamonbank.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class IndexPageController {



    @GetMapping("/")
    public String login() {
        return "index";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, 
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {
        // Invalidate session
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            request.getSession().invalidate();
        }
        
        // Add a parameter to show logout message
        redirectAttributes.addAttribute("logout", true);
        return "redirect:/";
    }
}
