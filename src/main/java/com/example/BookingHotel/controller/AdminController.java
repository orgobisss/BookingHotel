package com.example.BookingHotel.controller;

import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ClientRepository clientRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/clients")
    public String showAllClients(Model model) {
        List<Client> clients = clientRepository.findAll();

        // Принудительная инициализация списка комнат
        clients.forEach(client -> {
            if (client.getBookedRooms() != null) {
                client.getBookedRooms().size(); // или логирование для проверки
            }
        });

        model.addAttribute("clients", clients);
        return "clients";
    }
}
