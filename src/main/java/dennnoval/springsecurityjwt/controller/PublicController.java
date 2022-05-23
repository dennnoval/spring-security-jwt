package dennnoval.springsecurityjwt.controller;

import dennnoval.springsecurityjwt.entity.User;
import dennnoval.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PublicController {

  @Autowired
  private UserService service;

  @PostMapping("/register")
  public ResponseEntity<Object> create(@RequestBody Map<String, Object> json) {
    boolean isUsername = (json.get("username") != null && json.get("username") != "") ? true : false;
    boolean isPassword = (json.get("password") != null && json.get("password") != "") ? true : false;
    boolean isEmail = (json.get("email") != null && json.get("email") != "") ? true  : false;
    if ((isUsername || isPassword || isEmail) == false) {
      return ResponseEntity.status(406)
        .body("{\"error\":\"Fields cannot be null or empty value!\"}");
    }
    int status = 200;
    Object res = null;
    boolean isUsernameExists = service.isUsernameExists((String) json.get("username"));
    boolean isEmailExists = service.getByEmail((String) json.get("email")) != null ? true : false;
    if (isUsernameExists && isEmailExists) {
      res = "{\"error\":\"Username and Email already exists!\"}";
    } else if (isUsernameExists) {
      res = "{\"error\":\"Username already exists!\"}";
    } else if (isEmailExists) {
      res = "{\"error\":\"Email already exists!\"}";
    } else {
      User user = new User(
        (String) json.get("username"), new BCryptPasswordEncoder().encode((String) json.get("password")).toCharArray(),
        (String) json.get("email")
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

}
