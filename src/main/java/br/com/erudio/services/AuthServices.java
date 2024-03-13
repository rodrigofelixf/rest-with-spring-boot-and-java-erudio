package br.com.erudio.services;

import br.com.erudio.exceptions.InvalidJwtAuthenticationException;
import br.com.erudio.repositories.UserRepository;
import br.com.erudio.requests.v1.AccountCredentialsRequest;
import br.com.erudio.requests.v1.TokenResponse;
import br.com.erudio.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity signin(AccountCredentialsRequest data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new InvalidJwtAuthenticationException("Username " + username + " not found!"));

            var tokenResponse = new TokenResponse();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username//password supplied!");
        }

    }


}
