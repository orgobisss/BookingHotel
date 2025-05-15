package com.example.BookingHotel.service;

import com.example.BookingHotel.model.HotelRoom;
import com.example.BookingHotel.repository.HotelRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelRoomService {
    private final HotelRoomRepository hotelRoomRepository;

    public List<HotelRoom> getAllRoomsSortedByPriceDesc() {
        return hotelRoomRepository.findAllByOrderByPriceDesc();
    }
}