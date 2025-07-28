package agriwebb.Repository;

import agriwebb.Model.Livestock;
import agriwebb.Model.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivestockRepository extends JpaRepository<Livestock, Long> {


    @Query("SELECT l FROM Livestock l WHERE l.dbId = :dbId AND l.owner = :owner")
    Optional<Livestock> findByDbIdAndOwner(@Param("dbId") String dbId, @Param("owner") AppUser owner);


    @Query("SELECT DISTINCT l.dbId FROM Livestock l WHERE l.owner = :owner")
    List<String> findDistinctDbIdsByOwner(AppUser owner);

    @Query("SELECT COUNT(l) FROM Livestock l WHERE l.owner = :owner")
    Long countByOwner(@Param("owner") AppUser owner);


}
