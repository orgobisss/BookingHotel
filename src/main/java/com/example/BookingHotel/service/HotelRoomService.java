package com.example.BookingHotel.service;

import com.example.BookingHotel.model.HotelRoom;
import com.example.BookingHotel.repository.HotelRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRoomService {
    private final HotelRoomRepository hotelRoomRepository;

    public HotelRoomService(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    public List<HotelRoom> getAllRoomsSortedByPriceDesc() {
        return hotelRoomRepository.findAllByOrderByPriceDesc();
    }
}