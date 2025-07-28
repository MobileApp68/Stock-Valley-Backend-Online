package agriwebb.Service;

import agriwebb.DTO.*;
import agriwebb.Model.*;
import agriwebb.Repository.UserRepository;
import agriwebb.Security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    public ResponseEntity<?> register(RegisterRequest request) {

        if ((userRepository.findByEmail(request.getEmail())).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        AppUser user = new AppUser(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getUsername()
        );

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }



    public ResponseEntity<?> login(LoginRequest request) {
        Optional<AppUser> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        AppUser user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String token = jwtUtils.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token));

    }


    public ResponseEntity<?> autologin(@RequestBody String token) {
        boolean isValid = jwtUtils.validateToken(token);

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtUtils.extractEmail(token);
        Optional<AppUser> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    public ResponseEntity<?> getUsername(String token) {
        // Step 1: Extract email from token
        String email = jwtUtils.extractEmail(token);

        // Step 2: Query the database for the username
        Optional<String> optionalUsername = userRepository.findUserNameByEmail(email);


        if (optionalUsername.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username not found");
        }


        return ResponseEntity.ok(Collections.singletonMap("username",optionalUsername.get()));
    }



}
