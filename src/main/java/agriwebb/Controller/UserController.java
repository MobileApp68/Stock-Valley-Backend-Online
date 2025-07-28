package agriwebb.Controller;

import agriwebb.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/profile")
    public ResponseEntity<?> LoggedInUser(@RequestBody String token){

        return authService.getUsername(token);
    }
}
