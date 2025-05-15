package com.example.BookingHotel.service;

import com.example.BookingHotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.BookingHotel.model.Client;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomAuthProvider implements AuthenticationProvider {

    private final ClientRepository clientRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String phone = authentication.getCredentials().toString();

        Optional<Client> clientOpt = clientRepository.findByNameAndPhone(name, phone);

        if (clientOpt.isEmpty()) {
            throw new BadCredentialsException("Invalid name or phone");
        }

        Client client = clientOpt.get();

        return new UsernamePasswordAuthenticationToken(
                client.getEmail(), // principal
                null, // credentials (у нас нет пароля)
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + client.getRole()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}