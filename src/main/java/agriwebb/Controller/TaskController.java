package agriwebb.Controller;

import agriwebb.DTO.Task.Create_UpdateTask;


import agriwebb.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTasks(@RequestBody Create_UpdateTask request) {
        return taskService.createtask(request);
    }


    @PostMapping("/get")
    public ResponseEntity<?> viewTasks(@RequestBody String token){
        return taskService.getTasks(token);
    }


}
