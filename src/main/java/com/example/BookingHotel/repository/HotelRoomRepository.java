package com.example.BookingHotel.repository;

import com.example.BookingHotel.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
    // Сортировка по убыванию цены
    List<HotelRoom> findAllByOrderByPriceDesc();
}
