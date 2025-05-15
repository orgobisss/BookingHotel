package com.example.BookingHotel.controller;

import com.example.BookingHotel.service.HotelRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class HotelController {

    private final HotelRoomService hotelRoomService;

    @GetMapping
    public String home(Model model) {

        model.addAttribute("allRooms", hotelRoomService.getAllRoomsSortedByPriceDesc());
        return "home";
    }
}
