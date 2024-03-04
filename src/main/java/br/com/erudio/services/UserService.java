package br.com.erudio.services;

import br.com.erudio.exceptions.InvalidJwtAuthenticationException;
import br.com.erudio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    private Logger logger = Logger.getLogger(UserService.class.getName());


    @Override
    public UserDetails loadUserByUsername(String username) throws InvalidJwtAuthenticationException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidJwtAuthenticationException("Username " + username + " not found!"));
    }
}


