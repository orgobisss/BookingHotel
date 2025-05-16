package com.example.BookingHotel.service;

import com.example.BookingHotel.model.Booking;
import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.model.HotelRoom;
import com.example.BookingHotel.repository.BookingRepository;
import com.example.BookingHotel.repository.ClientRepository;
import com.example.BookingHotel.repository.HotelRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final HotelRoomRepository hotelRoomRepository;

    public BookingService(BookingRepository bookingRepository,
                          ClientRepository clientRepository,
                          HotelRoomRepository hotelRoomRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.hotelRoomRepository = hotelRoomRepository;
    }

    public List<HotelRoom> getRoomsByClient(String clientName) {
        Optional<Client> clientOpt = clientRepository.findByName(clientName);
        if (clientOpt.isEmpty()) {
            return Collections.emptyList(); // или выброс исключения
        }
        Client client = clientOpt.get();

        return bookingRepository.findByClient(client)
                .stream()
                .map(Booking::getRoom)
                .toList();
    }


    public void addBooking(Long roomId, String clientName) {
        Optional<Client> clientOpt = clientRepository.findByName(clientName);
        Optional<HotelRoom> roomOpt = hotelRoomRepository.findById(roomId);

        if (clientOpt.isPresent() && roomOpt.isPresent()) {
            HotelRoom room = roomOpt.get();

            if (room.isAvailable()) {
                room.setAvailable(false);
                hotelRoomRepository.save(room);

                Booking booking = new Booking();
                booking.setClient(clientOpt.get());
                booking.setRoom(room);
                bookingRepository.save(booking);
            }
        }
    }

    public List<HotelRoom> getRoomsByClient(Client client) {
        return bookingRepository.findByClient(client)
                .stream()
                .map(Booking::getRoom)
                .toList();
    }

    @Transactional
    public void deleteBooking(Long roomId, String clientName) {
        Optional<Client> clientOpt = clientRepository.findByName(clientName);
        Optional<HotelRoom> roomOpt = hotelRoomRepository.findById(roomId);

        if (clientOpt.isPresent() && roomOpt.isPresent()) {
            Client client = clientOpt.get();
            HotelRoom room = roomOpt.get();

            // Удаляем связь (если Booking — это @Entity с client и room)
            Booking booking = bookingRepository.findByClientAndRoom(client, room);
            if (booking != null) {
                bookingRepository.delete(booking);
            }

            // Освобождаем комнату
            room.setAvailable(true);
            hotelRoomRepository.save(room);
        }
    }

}
