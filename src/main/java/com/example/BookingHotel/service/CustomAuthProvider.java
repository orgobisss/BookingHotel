package com.example.BookingHotel.service;

import com.example.BookingHotel.model.Client;
import com.example.BookingHotel.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String phone = authentication.getCredentials().toString();

        Optional<Client> clientOpt = clientRepository.findByNameAndPhone(name, phone);

        if (clientOpt.isEmpty()) {
            throw new BadCredentialsException("Неверное имя или номер телефона");
        }

        return new UsernamePasswordAuthenticationToken(name, phone, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
