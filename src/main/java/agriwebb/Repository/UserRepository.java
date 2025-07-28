package agriwebb.Repository;

import agriwebb.Model.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);

    @Query("SELECT U.username FROM AppUser U WHERE U.email = :email")
    Optional<String> findUserNameByEmail(@Param("email") String email);


}
