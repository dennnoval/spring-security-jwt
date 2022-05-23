package dennnoval.springsecurityjwt;

import static org.assertj.core.api.Assertions.assertThat;

import dennnoval.springsecurityjwt.controller.PublicController;
import dennnoval.springsecurityjwt.controller.UserController;
import dennnoval.springsecurityjwt.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class EndpointTests {

  @Autowired
  private UserController userController;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private PublicController publicController;

  /*@Test
  void getByIdEndpoint_Tests() {
    Map<String, String> json = new HashMap<>();
    json.put("username", "user1");
    assertThat(userController.getById(json)).isNotNull();
  }*/

  /*@Test
  void getByIdEndpoint_Tests() {
    Map<String, String> json = new HashMap<>();
    json.put("email", "user1@spring.com");
    assertThat(userController.getByEmail(json)).isNotNull();
  }*/

  /*@Test
  void getAllUsers_test() {
    assertThat(userController.index()).isNull();
  }*/

  /*@Test
  void register_Test() {
    Map<String, Object> json = new HashMap<>();
    json.put("username", (String) "user1@spring.com");
    json.put("password", (char[]) encoder.encode("1234").toCharArray());
    json.put("email", (String) "user1@spring.com");
    json.put("role", (Role) Role.USER);
    assertThat(publicController.create(json)).isNotNull();
  }*/

}
