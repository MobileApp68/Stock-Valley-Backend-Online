package agriwebb.Service;

import agriwebb.DTO.Task.TaskArray;
import agriwebb.DTO.Tools.CreateTools;
import agriwebb.DTO.Tools.GetTools;
import agriwebb.DTO.Tools.ToolsArray;
import agriwebb.Model.AppUser;
import agriwebb.Model.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import agriwebb.Repository.UserRepository;
import agriwebb.Security.JWTUtils;
import agriwebb.Repository.ToolsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToolService {


    @Autowired ToolsRepository toolsRepository;
    @Autowired JWTUtils jwtUtils;
    @Autowired UserRepository userRepository;


    public ResponseEntity<?> createtools(CreateTools request) {

        String token = request.getOwnerToken();
        String email = jwtUtils.extractEmail(token);

        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2: Fetch tools already in database
        List<Tools> existingTools = toolsRepository.findAllByOwner(owner);

        // Step 3: Extract new tool IDs from request
        List<String> newToolIds = request.getTools().stream()
                .map(ToolsArray::getId)
                .toList();

        // Step 4: Delete tools from DB that are not in the new list
        for (Tools existingTool : existingTools) {
            if (!newToolIds.contains(existingTool.getId())) {
                toolsRepository.delete(existingTool);
            }
        }

        // Step 5: Save or update submitted tools
        for (ToolsArray dtoTool : request.getTools()) {
            Tools tool = new Tools();
            tool.setId(dtoTool.getId());
            tool.setToolName(dtoTool.getToolName());
            tool.setQuantity(dtoTool.getQuantity());
            tool.setUser(owner); // very important to set the owner again

            toolsRepository.save(tool); // will upsert by ID
        }

        return ResponseEntity.ok("Tools successfully updated");
    }


    public ResponseEntity<?> getTools(String token) {
        // Step 1: Extract email from token
        String email = jwtUtils.extractEmail(token);

        // Step 2: Find the user
        AppUser owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Tools> toolList = toolsRepository.findAllByOwner(owner);

        if (toolList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<ToolsArray> toolDTOs = toolList.stream()
                .map(tool -> new ToolsArray(
                        tool.getId(),
                        tool.getToolName(),
                        tool.getQuantity()
                ))
                .collect(Collectors.toList());

        GetTools response = new GetTools(toolDTOs);

        return ResponseEntity.ok(response);
    }


}
