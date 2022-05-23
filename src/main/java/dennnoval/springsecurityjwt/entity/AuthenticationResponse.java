package dennnoval.springsecurityjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@NoArgsConstructor
public class AuthenticationResponse implements Serializable {

  public static HashMap<String, char[]> successToken1(char[] accessToken) {
    HashMap<String, char[]> json = new HashMap<String, char[]>();
    json.put("access_token", accessToken);
    return json;
  }

  public static HashMap<String, char[]> successToken2(char[] accessToken, char[] refreshToken) {
    HashMap<String, char[]> json = new HashMap<String, char[]>();
    json.put("access_token", accessToken);
    json.put("refresh_token", refreshToken);
    return json;
  }

  // For Error Response
  public static HashMap<String, char[]> error(char[] errorMessage) {
    HashMap<String, char[]> json = new HashMap<String, char[]>();
    json.put("error", errorMessage);
    return json;
  }

}
