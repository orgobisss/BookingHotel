package com.example.BookingHotel.repository;

import com.example.BookingHotel.model.Booking;
import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByClient(Client client);
    Booking findByClientAndRoom(Client client, HotelRoom room);
}
