package com.example.BookingHotel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientLoginDto {
    @NotBlank(message = "Имя обязательно")
    private String name;

    @NotBlank(message = "Телефон обязателен")
    private String phone;
}