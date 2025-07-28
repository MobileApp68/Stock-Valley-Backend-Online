package agriwebb.Service;

import agriwebb.DTO.Livestock.CreateLivestock;
import agriwebb.DTO.Livestock.GetLivestock;
import agriwebb.Model.AppUser;
import agriwebb.Model.Livestock;
import agriwebb.Repository.LivestockRepository;
import agriwebb.Repository.UserRepository;
import agriwebb.Security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LivestockService {

    @Autowired
    private LivestockRepository livestockRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;



    public ResponseEntity<?> createLivestock(CreateLivestock request){

        String token=request.getOwnerToken();
        String email= jwtUtils.extractEmail(token);



        String dbId= request.getDbId();

        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if ((livestockRepository.findByDbIdAndOwner(dbId,owner).isPresent())){

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Livestock newlivestock= new Livestock(
                request.getDbId(),
                request.getAnimalType(),
                request.getQuantity(),
                request.getBreed(),
                request.getTagId(),
                request.getAge(),
                request.getHealthStatus(),
                request.getNotes(),
                owner
        );

        livestockRepository.save(newlivestock);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> getLivestock(String dbId, String token){

        String email= jwtUtils.extractEmail(token);

        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(""));

        Optional<Livestock> viewLivestock = livestockRepository.findByDbIdAndOwner(dbId,owner);

        if (viewLivestock.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        Livestock livestock= viewLivestock.get();

        GetLivestock getLivestock= new GetLivestock(
                livestock.getDbId(),
                livestock.getAnimalType(),
                livestock.getQuantity(),
                livestock.getBreed(),
                livestock.getTagId(),
                livestock.getAge(),
                livestock.getHealthStatus(),
                livestock.getNotes()
        );

        return ResponseEntity.ok(getLivestock);

    }




    public ResponseEntity<?> updateLivestock(CreateLivestock request) {
        String token = request.getOwnerToken();
        String email = jwtUtils.extractEmail(token);
        String dbId = request.getDbId();

        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Livestock> existingLivestock = livestockRepository.findByDbIdAndOwner(dbId, owner);

        if (existingLivestock.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //Store current livestock for update
        Livestock livestock = existingLivestock.get();

        // Update fields
        livestock.setDbId(request.getDbId());
        livestock.setAnimalType(request.getAnimalType());
        livestock.setAge(request.getAge());
        livestock.setNotes(request.getNotes());
        livestock.setBreed(request.getBreed());
        livestock.setQuantity(request.getQuantity());
        livestock.setTagId(request.getTagId());
        livestock.setHealthStatus(request.getHealthStatus());
        livestock.setOwner(owner);

        Livestock updatedLivestock = livestockRepository.save(livestock);

        return ResponseEntity.ok(updatedLivestock);
    }


    public ResponseEntity<?> deletelivestock(String dbId, String token) {
        try {
            String email = jwtUtils.extractEmail(token);
            AppUser owner = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Optional<Livestock> clearinglivestock = livestockRepository.findByDbIdAndOwner(dbId, owner);

            if (clearinglivestock.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            livestockRepository.delete(clearinglivestock.get());
            return ResponseEntity.ok().build();

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public ResponseEntity<?> DBID_List(String token) {

        String email = jwtUtils.extractEmail(token);

        // Step 2: Find the user by email
        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 3: Use the custom query to fetch all DB IDs for that user
        List<String> dbIds = livestockRepository.findDistinctDbIdsByOwner(owner);


        return ResponseEntity.ok(dbIds);
    }


    public ResponseEntity<?> countLivestock(String token) {
        try {

            String email = jwtUtils.extractEmail(token);

            AppUser owner = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));


            Long count = livestockRepository.countByOwner(owner);

            return ResponseEntity.ok(Collections.singletonMap("livestockCount",count));
        }

        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }



}
