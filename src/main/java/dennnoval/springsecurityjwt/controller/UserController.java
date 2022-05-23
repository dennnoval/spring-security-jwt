package dennnoval.springsecurityjwt.controller;

import dennnoval.springsecurityjwt.entity.User;
import dennnoval.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping("/getById")
  public ResponseEntity<Object> getById() {
    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    User user = service.getById(username);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping("/update")
  public ResponseEntity<String> update(@RequestBody Map<String, String> json) {
    int status = 200;
    String res = null;
    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    User user = service.getById(username);
    String password = json.get("password");
    String email = json.get("email");
    if (password != null && password != "")
      user.setPassword(new BCryptPasswordEncoder().encode(password).toCharArray());
    if (email != null && email != "")
      user.setEmail(email);
    boolean isUpdated = service.update(user);
    if (isUpdated) {
      status = 202;
      res = "{\"success\":\"User has been successfully updated!\"}";
    } else {
      status = 406;
      res = "{\"error\":\"Declared fields cannot be null or empty value!\"}";
    }
    return ResponseEntity.status(status).body(res);
  }

}