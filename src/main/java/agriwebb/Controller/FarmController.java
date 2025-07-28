package agriwebb.Controller;

import agriwebb.DTO.FarmRequest;
import agriwebb.Service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;


    @PostMapping
    public ResponseEntity<?> createFarm(@RequestBody FarmRequest request) {
        return farmService.createFarm(request);
    }

    @GetMapping
    public ResponseEntity<?> loadfarm(@RequestParam String token){
        return farmService.loadFarm(token);

    }

}
