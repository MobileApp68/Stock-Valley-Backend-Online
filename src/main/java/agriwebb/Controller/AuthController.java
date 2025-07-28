package agriwebb.Controller;

import agriwebb.DTO.LoginRequest;
import agriwebb.DTO.RegisterRequest;
import agriwebb.Model.AppUser;
import agriwebb.Repository.UserRepository;
import agriwebb.Service.AuthService;
import agriwebb.Security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils tokenService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }



    @PostMapping("/autologin")
    public ResponseEntity<?> autologin(@RequestBody String token) {

        return authService.autologin(token);

    }


}
