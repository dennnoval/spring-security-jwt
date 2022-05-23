package dennnoval.springsecurityjwt.repository;

import dennnoval.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
  
  // @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
  Optional<User> findByUsernameAndPassword(String username, char[] password);

  Optional<User> findByEmail(String email);
}
