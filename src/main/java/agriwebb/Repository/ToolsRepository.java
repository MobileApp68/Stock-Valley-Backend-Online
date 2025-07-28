package agriwebb.Repository;

import agriwebb.Model.AppUser;
import agriwebb.Model.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ToolsRepository extends JpaRepository<Tools, Long> {

    // Fetch all tools belonging to a user
    List<Tools> findAllByOwner(AppUser owner);

}

