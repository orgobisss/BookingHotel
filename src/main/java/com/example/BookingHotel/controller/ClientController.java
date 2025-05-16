package com.example.BookingHotel.controller;

import com.example.BookingHotel.dto.ClientLoginDto;
import com.example.BookingHotel.dto.ClientRegistrationDto;
import com.example.BookingHotel.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String registerClient(@ModelAttribute("client") @Valid ClientRegistrationDto dto,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "register"; // возвращаем на форму с ошибками
        }
        clientService.registerClient(dto);
        return "redirect:/login";
    }

    // Логин
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("client", new ClientLoginDto());
        return "login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            clientService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Клиент успешно удалён");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при удалении клиента");
        }
        return "redirect:/clients";
    }
}
