package com.example.BookingHotel.service;

import com.example.BookingHotel.dto.ClientRegistrationDto;
import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public void registerClient(ClientRegistrationDto dto) {
        if (clientRepository.existsByPhone(dto.getPhone())) {
            throw new RuntimeException("Phone already registered");
        }

        Client client = new Client();
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setRole("ROLE_USER"); // именно с "ROLE_", иначе Spring Security не распознает
        clientRepository.save(client);
    }
}
