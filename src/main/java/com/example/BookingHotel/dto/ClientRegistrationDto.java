package com.example.BookingHotel.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data  // Lombok (геттеры + сеттеры)
public class ClientRegistrationDto {

    @NotBlank(message = "Имя обязательно")
    private String name;

    @NotBlank(message = "Фамилия обязательна")
    private String surname;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Некорректный номер телефона")
    private String phone;
}