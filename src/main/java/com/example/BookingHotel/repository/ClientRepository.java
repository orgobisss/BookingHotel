package com.example.BookingHotel.repository;

import com.example.BookingHotel.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNameAndPhone(String name, String phone);
    Optional<Client> findByName(String name);
    boolean existsByPhone(String phone);
}
