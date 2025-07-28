package agriwebb.Repository;


import agriwebb.Model.AppUser;
import agriwebb.Model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FarmRepository extends JpaRepository<Farm, Long> {
    Optional<Farm> findByOwner(AppUser owner);

}
