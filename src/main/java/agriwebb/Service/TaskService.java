package agriwebb.Service;

import agriwebb.DTO.Task.Create_UpdateTask;
import agriwebb.DTO.Task.GetTask;
import agriwebb.DTO.Task.TaskArray;
import agriwebb.Model.AppUser;
import agriwebb.Model.Task;
import agriwebb.Repository.TaskRepository;
import agriwebb.Repository.UserRepository;
import agriwebb.Security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TaskService {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;



    public ResponseEntity<?> createtask(Create_UpdateTask request) {

        // Step 1: Get user info from token
        String email = jwtUtils.extractEmail(request.getOwnerToken());

        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        List<Task> existingTasks = taskRepository.findAllByOwner(owner);
        Set<String> existingTaskIds = existingTasks.stream()
                .map(Task::getId)
                .collect(Collectors.toSet());


        // Step 3: Get incoming task IDs from the frontend
        Set<String> incomingTaskIds = request.getTasks().stream()
                .map(TaskArray::getId)
                .collect(Collectors.toSet());

        // Step 4: Delete tasks that exist in DB but not in incoming list
        for (Task task : existingTasks) {
            if (!incomingTaskIds.contains(task.getId())) {
                taskRepository.delete(task);
            }
        }

        // Step 5: Save or update tasks from the incoming list
        for (TaskArray taskData : request.getTasks()) {
            Optional<Task> existingTaskOpt = taskRepository.findByTaskId(taskData.getId());

            if (existingTaskOpt.isPresent()) {
                Task existingTask = existingTaskOpt.get();
                existingTask.setTitle(taskData.getTitle());
                existingTask.setSelected(taskData.isSelected());
                existingTask.setAdditionalNotes(request.getAdditionalNotes());
                taskRepository.save(existingTask);
            } else {
                Task newTask = new Task(
                        taskData.getId(),
                        taskData.getTitle(),
                        taskData.isSelected(),
                        request.getAdditionalNotes(),
                        owner
                );
                taskRepository.save(newTask);
            }
        }

        return ResponseEntity.ok("Tasks saved successfully");
    }



    public ResponseEntity<?> getTasks(String ownerToken) {
        String email = jwtUtils.extractEmail(ownerToken);

        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Task> taskList = taskRepository.findAllByOwner(owner);

        if (taskList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No tasks found for this user");
        }

        // Get the shared note (same for all tasks)
        String note = taskRepository.findDistinctNoteByOwner(owner)
                .orElse(""); // or whatever default you prefer

        List<TaskArray> taskDTOs = taskList.stream()
                .map(task -> new TaskArray(
                        task.getId(),
                        task.getTitle(),
                        task.isSelected()
                ))
                .collect(Collectors.toList());

        GetTask response = new GetTask(taskDTOs, note);
        return ResponseEntity.ok(response);
    }

}


