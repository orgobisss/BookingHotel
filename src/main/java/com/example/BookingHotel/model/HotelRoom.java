package com.example.BookingHotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "hotel_rooms")
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private int capacity;
    private int price;
    private boolean available;
    private String description;
    private String image;

    @ManyToMany(mappedBy = "bookedRooms")
    private Set<Client> clients = new HashSet<>();

    @Override
    public String toString() {
        return "HotelRoom{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", available=" + available +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
