package agriwebb.Controller;


import agriwebb.DTO.Livestock.CreateLivestock;
import agriwebb.Service.LivestockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/livestock")
public class LivestockController {

    @Autowired LivestockService livestockService;


@PostMapping("/create")
    public ResponseEntity<?> createlivestock(@RequestBody CreateLivestock newlivestock){

    return livestockService.createLivestock(newlivestock);
}


@PostMapping("/get")
    public ResponseEntity<?> getlivestock(@RequestBody Map<String, String> request) {
        return livestockService.getLivestock(request.get("dbId"), request.get("token"));
    }


@PostMapping("/update")
    public ResponseEntity<?> updatelivestock(@RequestBody CreateLivestock livestockupdate){

    return livestockService.updateLivestock(livestockupdate);

}


@PostMapping("/delete")
    public ResponseEntity<?> deletelivestock(@RequestBody Map<String, String> request){
    return livestockService.deletelivestock(request.get("dbId"), request.get("token"));
}



@PostMapping("/session-ids")
    public ResponseEntity<?> getDbIds(@RequestBody String token) {

    return livestockService.DBID_List(token);
    }



    @PostMapping("/count")
    public ResponseEntity<?> getNumber(@RequestBody String token){

    return livestockService.countLivestock(token);
    }

}