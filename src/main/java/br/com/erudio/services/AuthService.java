package br.com.erudio.services;

import br.com.erudio.exceptions.InvalidJwtAuthenticationException;
import br.com.erudio.repositories.UserRepository;
import br.com.erudio.requests.v1.AccountCredentialsRequest;
import br.com.erudio.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity signin(AccountCredentialsRequest data) {
        try {
            var username = data.getUserName();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));

            var tokenResponse = Optional.ofNullable(user)
                    .map(u -> tokenProvider.createAccessToken(username, u.getRoles()))
                    .orElseThrow(() -> new InvalidJwtAuthenticationException("Failed to create access token."));

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username//password supplied!");
        }

    }


}
