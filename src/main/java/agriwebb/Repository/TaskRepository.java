package agriwebb.Repository;


import agriwebb.Model.AppUser;
import agriwebb.Model.Livestock;
import agriwebb.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOwner(AppUser owner);

    @Query("SELECT DISTINCT t.additionalNotes FROM Task t WHERE t.owner = :owner")
    Optional<String> findDistinctNoteByOwner(@Param("owner") AppUser owner);

    @Query("SELECT t FROM Task t WHERE t.id = :id")
    Optional<Task> findByTaskId(String id);

}
