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

        System.out.println("==> registerClient вызван");

        if (clientRepository.existsByPhone(dto.getPhone())) {
            System.out.println("==> Клиент с таким телефоном уже есть");
            throw new RuntimeException("Phone already registered");
        }

        Client client = new Client();
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setRole("ROLE_USER"); // именно с "ROLE_", иначе Spring Security не распознает


        System.out.println("Сохраняем клиента: " + client);
        clientRepository.save(client);

    }

    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
