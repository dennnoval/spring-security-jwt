package dennnoval.springsecurityjwt.service;

import dennnoval.springsecurityjwt.entity.User;
import dennnoval.springsecurityjwt.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserService {

  @Autowired
  private UserRepository repo;

  public List<User> getAll() {
    List<User> users = new ArrayList<>();
    repo.findAll().forEach(user -> users.add(user));
    return users;
  }

  public User getById(String id) {
    try {
      return repo.findById(id).get();
    } catch (Exception ex) {
      log.error("Error: User doesn't exists!");
    }
    return null;
  }

  public User getByEmail(String email) {
    try {
      return repo.findByEmail(email).get();
    } catch (Exception ex) {
      log.error("Error: User doesn't exists!");
    }
    return null;
  }

  public boolean isUsernameExists(String username) {
    boolean isExists = false;
    if (repo.existsById(username)) {
      isExists = true;
      log.info("Username already exists!");
    } else {
      log.info("Username doesn't exists!");
    }
    return isExists;
  }

  public boolean create(User user) {
    try {
      repo.save(user);
      return true;
    } catch (Exception ex) {
      log.error("Error: Field cannot be null or empty!");
    }
    return false;
  }

  public boolean update(User user) {
    try {
      repo.save(user);
      return true;
    } catch (Exception ex) {
      log.error("Error: Field cannot be null or empty!");
    }
    return false;
  }

  public boolean deleteById(String id) {
    try {
      repo.deleteById(id);
      return true;
    } catch (Exception ex) {
      log.error("Error: User doesn't exists!");
    }
    return false;
  }

}
