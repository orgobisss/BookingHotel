package com.example.BookingHotel.controller;

import com.example.BookingHotel.dto.ClientLoginDto;
import com.example.BookingHotel.dto.ClientRegistrationDto;
import com.example.BookingHotel.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    // Регистрация
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("client", new ClientRegistrationDto());
        return "register";
    }

    @PostMapping("/saveClient")
    public String registerClient(@ModelAttribute("client") ClientRegistrationDto dto) {
        System.out.println("Регистрация: " + dto.getName() + " " + dto.getPhone());
        clientService.registerClient(dto);
        return "redirect:/login";
    }

    // Логин
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("client", new ClientLoginDto());
        return "login";
    }
}
