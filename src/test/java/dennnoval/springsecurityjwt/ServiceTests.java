package dennnoval.springsecurityjwt;

import dennnoval.springsecurityjwt.entity.User;
import dennnoval.springsecurityjwt.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceTests {

  @Autowired
  private UserService service;

  /*@Test
  void getUserById_Test() {
    String username = "dennnoval";
    // User user = service.getById(username);
    Assertions.assertThat(service.getById(username)).isNotNull();
  }*/

  /*@Test
  void createUser_Test() {
    String username = "user1";
    char[] password = "1234".toCharArray();
    String email = "user1@spring.com";
    String role = "USER";
    User user = new User(username, password, email,role);

    Assertions.assertThat(service.create(user)).isTrue();
  }*/

  /*@Test
  void updateUser_Test() {
    User user = service.getById("user1");
    user.setRole("ADMIN");

    Assertions.assertThat(service.update(user)).isTrue();
  }*/

  /*@Test
  void deleteUserById_Test() {
    String id = "user2";

    Assertions.assertThat(service.deleteById(id)).isTrue();
  }*/

  /*@Test
  void getByEmail_Test() {
    String email = "user12@spring.com";
    Assertions.assertThat(service.getByEmail(email)).isNotNull();
  }*/

  /*@Test
  void getAllUser_Test() {
    Assertions.assertThat(service.getAll()).isNotNull();
  }*/

  @Test
  void isEmailExists_Test() {
    String email = "user12@spring.com";
    Assertions.assertThat(service.getByEmail(email)).isNull();
  }

}
