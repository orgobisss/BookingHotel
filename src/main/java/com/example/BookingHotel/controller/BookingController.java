package com.example.BookingHotel.controller;

import com.example.BookingHotel.model.Booking;
import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.model.HotelRoom;
import com.example.BookingHotel.repository.ClientRepository;
import com.example.BookingHotel.repository.HotelRoomRepository;
import com.example.BookingHotel.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showBookings(Model model, Principal principal) {
        List<HotelRoom> rooms = bookingService.getRoomsByClient(principal.getName());
        model.addAttribute("bookedRooms", rooms);
        return "bookings";
    }


    @PostMapping("/add/{roomId}")
    public String addBooking(@PathVariable Long roomId, Principal principal) {
        bookingService.addBooking(roomId, principal.getName());
        return "redirect:/";
    }


    @PostMapping("/delete/{roomId}")
    public String deleteBooking(@PathVariable Long roomId, Principal principal) {
        bookingService.deleteBooking(roomId, principal.getName());
        return "redirect:/bookings";
    }
}
