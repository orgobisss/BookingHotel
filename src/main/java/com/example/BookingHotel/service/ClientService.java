package com.example.BookingHotel.service;

import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client AddClient(Client client) {
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
    }
}
