package br.com.erudio.controller;


import br.com.erudio.requests.v1.AccountCredentialsRequest;
import br.com.erudio.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication EndPoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsRequest data) {
        if (isInvalidCredentials(data)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        var token = authService.signin(data);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh/{userName}")
    public ResponseEntity refreshToken(@PathVariable("userName") String username, @RequestHeader("Authorization") String refreshToken) {
        if (isInvalidRefreshToken(username, refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        var token = authService.refreshToken(username, refreshToken);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        return ResponseEntity.ok(token);
    }

    private boolean isInvalidCredentials(AccountCredentialsRequest data) {
        return data == null ||
                isNullOrBlank(data.getUserName()) ||
                isNullOrBlank(data.getPassword());
    }

    private boolean isInvalidRefreshToken(String username, String refreshToken) {
        return isNullOrBlank(username) || isNullOrBlank(refreshToken);
    }

    private boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }
}
