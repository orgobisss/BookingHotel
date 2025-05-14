package com.example.BookingHotel.repository;

import com.example.BookingHotel.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM clients WHERE phone = :phone", nativeQuery = true)
    Optional<Client> FindByPhone(String phone);
}
