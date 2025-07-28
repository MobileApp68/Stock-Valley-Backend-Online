package agriwebb.Service;

import agriwebb.DTO.FarmRequest;
import agriwebb.DTO.FarmResponse;
import agriwebb.Model.AppUser;
import agriwebb.Model.Farm;
import agriwebb.Repository.FarmRepository;
import agriwebb.Repository.UserRepository;
import agriwebb.Security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<?> createFarm(@RequestBody FarmRequest farm) {

        String token = farm.getOwnertoken();
        String email = jwtUtils.extractEmail(token);


        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Farm> existingFarm = farmRepository.findByOwner(owner);

        Farm farmInfo;
        if (existingFarm.isPresent()) {
            farmInfo = existingFarm.get();
            farmInfo.setName(farm.getName());
            farmInfo.setLatitude(farm.getLatitude());
            farmInfo.setLongitude(farm.getLongitude());

        }
        else {
            farmInfo = new Farm(
                    farm.getName(),
                    farm.getLatitude(),
                    farm.getLongitude(),
                    owner
            );
        }
        Farm savedFarm = farmRepository.save(farmInfo);
        return ResponseEntity.ok(savedFarm);
    }



    public ResponseEntity<?> loadFarm(@RequestParam String usertoken) {
        String email = jwtUtils.extractEmail(usertoken);

        Optional<AppUser> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        AppUser user = userOptional.get();

        Optional<Farm> farmOptional = farmRepository.findByOwner(user);
        if (farmOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Farm farm = farmOptional.get();


        FarmResponse response = new FarmResponse(
                farm.getName(),
                farm.getLatitude(),
                farm.getLongitude()
        );

        return ResponseEntity.ok(response);
    }

}
