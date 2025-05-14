package com.example.BookingHotel.controller;

import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @PostMapping
    public Client AddClient(@RequestBody Client client) {
        return clientService.AddClient(client);
    }

    @DeleteMapping(path = "{id}")
    public void DeleteClient(@PathVariable Long id) {
        clientService.DeleteClient(id);
    }
}
