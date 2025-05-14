package com.example.BookingHotel.controller;

import com.example.BookingHotel.service.HotelRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HotelController {

    private final HotelRoomService hotelRoomService;

    public HotelController(HotelRoomService hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
    }

    @GetMapping
    public String home(Model model) {

        model.addAttribute("allRooms", hotelRoomService.getAllRoomsSortedByPriceDesc());
        return "home";
    }
}
