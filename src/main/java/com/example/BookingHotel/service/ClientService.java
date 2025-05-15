package com.example.BookingHotel.service;

import com.example.BookingHotel.dto.ClientRegistrationDto;
import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void register(ClientRegistrationDto dto) {
        if (clientRepository.existsByPhone(dto.getPhone())) {
            throw new RuntimeException("Phone already registered");
        }

        Client client = new Client();
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());

        clientRepository.save(client);
    }


    /*public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // Возвращает имя пользователя (например, email)
    }*/

/*    public Client AddClient(Client client) {
        Optional<Client> optionalClient = clientRepository.FindByPhone(client.getPhone());
        if (optionalClient.isPresent()) {
            throw new IllegalStateException("клиент с таким номером уже есть");
        }
        return clientRepository.save(client);
    }

    public void DeleteClient(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException("клиента с id: " + id + " не существует");
        }
        clientRepository.deleteById(id);
    }*/
}
