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
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new ClientRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("client") ClientRegistrationDto dto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        clientService.register(dto);
        return "redirect:/login?registered";
    }

    // Логин
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("client", new ClientLoginDto());
        return "login";
    }

 /*   @PostMapping
    public Client AddClient(@RequestBody Client client) {
        return clientService.AddClient(client);
    }

    @DeleteMapping(path = "{id}")
    public void DeleteClient(@PathVariable Long id) {
        clientService.DeleteClient(id);
    }*/
}
