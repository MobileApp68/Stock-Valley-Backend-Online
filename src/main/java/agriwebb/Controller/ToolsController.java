package agriwebb.Controller;


import agriwebb.DTO.Tools.CreateTools;
import agriwebb.Service.ToolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/tools")
public class ToolsController {


    @Autowired ToolService toolService;

    @PostMapping("/create")
    public ResponseEntity<?> createtool(@RequestBody CreateTools request){

        return toolService.createtools(request);
    }


    @PostMapping("/get")
    public ResponseEntity<?>  viewtools(@RequestBody String token){
        return toolService.getTools(token);
    }

}
