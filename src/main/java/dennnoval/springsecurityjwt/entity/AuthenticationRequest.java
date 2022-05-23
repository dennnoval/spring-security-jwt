package dennnoval.springsecurityjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationRequest implements Serializable {

  private String username;
  private String password;

}
