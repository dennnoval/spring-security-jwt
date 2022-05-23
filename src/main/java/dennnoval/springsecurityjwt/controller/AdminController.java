package dennnoval.springsecurityjwt.controller;

import dennnoval.springsecurityjwt.entity.Role;
import dennnoval.springsecurityjwt.entity.User;
import dennnoval.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private UserService service;

  @GetMapping("/getAll")
  public ResponseEntity<Object> getAll() {
    List<User> users = service.getAll();
    Map<String, Object> json = new HashMap<>();
    json.put("users", (Object) users);
    return ResponseEntity.ok().body(json);
  }

  @PostMapping("/getById")
  public ResponseEntity<Object> getById(@RequestBody Map<String, String> json) {
    boolean username = (json.get("username") != null && json.get("username") != "") ? true : false;
    if (!username) {
      return ResponseEntity.status(406)
        .body("{\"error\":\"Username cannot be null or empty value!\"}");
    }
    User user = service.getById(json.get("username"));
    int status = 200;
    Object res = null;
    res = user;
    if (json.get("username") == null) {
      res = "{\"error\":\"Not acceptable request format!\"}";
      status = 406;
    } else if (user == null) {
      res = "{\"error\":\"User doesn't exists!\"}";
    }
    return ResponseEntity.status(status).body(res);
  }

  @PostMapping("/getByEmail")
  public ResponseEntity<Object> getByEmail(@RequestBody Map<String, String> json) {
    boolean email = (json.get("email") != null && json.get("email") != "") ? true : false;
    if (!email) {
      return ResponseEntity.status(406)
        .body("{\"error\":\"Email cannot be null or empty value!\"}");
    }
    User user = service.getByEmail(json.get("email"));
    int status = 200;
    Object res = null;
    res = user;
    if (json.get("email") == null) {
      res = "{\"error\":\"Not acceptable request format!\"}";
      status = 406;
    } else if (user == null) {
      res = "{\"error\":\"User doesn't exists!\"}";
    }
    return ResponseEntity.status(status).body(res);
  }

  @PostMapping("/create")
  public ResponseEntity<String> create(@RequestBody Map<String, String> json) {
    boolean isUsername = (json.get("username") != null && json.get("username") != "") ? true : false;
    boolean isPassword = (json.get("password") != null && json.get("password") != "") ? true : false;
    boolean isEmail = (json.get("email") != null && json.get("email") != "") ? true  : false;
    boolean isRole = (json.get("role") != null && json.get("role") != "") ? true : false;
    if ((isUsername || isPassword || isEmail || isRole) == false) {
      return ResponseEntity.status(406)
        .body("{\"error\":\"Fields cannot be null or empty value!\"}");
    }
    int status = 200;
    String res = null;
    boolean isUsernameExists = service.isUsernameExists(json.get("username"));
    boolean isEmailExists = service.getByEmail(json.get("email")) != null ? true : false;
    if (isUsernameExists && isEmailExists) {
      res = "{\"error\":\"Username and Email already exists!\"}";
    } else if (isUsernameExists) {
      res = "{\"error\":\"Username already exists!\"}";
    } else if (isEmailExists) {
      res = "{\"error\":\"Email already exists!\"}";
    } else {
      Role role = (json.get("role") != null && json.get("role") != "")
        ? Role.getValueOf(json.get("role")) : Role.USER;

      User user = new User(
        json.get("username"), new BCryptPasswordEncoder().encode(json.get("password")).toCharArray(),
        json.get("email"), role
      );
      boolean isCreated = service.create(user);
      if (isCreated) {
        status = 201;
        res = "{\"success\":\"New user has been successfully created!\"}";
      } else {
        status = 406;
        res = "{\"error\":\"Field cannot be null or empty!\"}";
      }
    }
    return ResponseEntity.status(status).body(res);
  }

  @PostMapping("/update")
  public ResponseEntity<String> update(@RequestBody Map<String, String> json) {
    boolean isUsername = (json.get("username") != null && json.get("username") != "") ? true : false;
    if (!isUsername) {
      return ResponseEntity.status(406)
        .body("{\"error\":\"Fields cannot be null or empty value!\"}");
    }
    int status = 200;
    String res = null;
    boolean isUsernameExists = service.isUsernameExists(json.get("username"));
    if (isUsernameExists) {
      User user = service.getById(json.get("username"));
      String password = json.get("password");
      String email = json.get("email");
      Role role = (json.get("role") != null && json.get("role") != "")
        ? Role.getValueOf(json.get("role")) : null;

      if (password != null && password != "")
        user.setPassword(new BCryptPasswordEncoder().encode(json.get("password")).toCharArray());
      if (email != null && email != "")
        user.setEmail(email);
      if (role != null)
        user.setRole(role);
      boolean isUpdated = service.update(user);
      if (isUpdated) {
        status = 202;
        res = "{\"success\":\"User has been successfully updated!\"}";
      } else {
        status = 406;
        res = "{\"error\":\"Declared field's value cannot be null or empty!\"}";
      }
    } else {
      res = "{\"error\":\"User doesn't exists!\"}";
    }
    return ResponseEntity.status(status).body(res);
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteById(@RequestBody Map<String, String> json) {
    boolean username = (json.get("username") != null && json.get("username") != "") ? true : false;
    if (!username) {
      return ResponseEntity.status(406)
        .body("{\"error\":\"Username cannot be null or empty value!\"}");
    }
    int status = 200;
    String res = null;
    boolean isDeleted = service.deleteById(json.get("username"));
    if (isDeleted) {
      res = "{\"success\":\"User has been successfully deleted!\"}";
    } else {
      res = "{\"error\":\"User doesn't exists!\"}";
    }
    return ResponseEntity.status(status).body(res);
  }

}
