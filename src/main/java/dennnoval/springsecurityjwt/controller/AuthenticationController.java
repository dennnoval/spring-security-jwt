package dennnoval.springsecurityjwt.controller;

import dennnoval.springsecurityjwt.entity.AuthenticationRequest;
import dennnoval.springsecurityjwt.entity.AuthenticationResponse;
import dennnoval.springsecurityjwt.utility.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/authenticate")
  public ResponseEntity<? extends Object> authenticate(@RequestBody AuthenticationRequest authReq) {
    String username = authReq.getUsername();
    String password = authReq.getPassword();
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
      username, password
    );
    Authentication authentication = null;
    try {
      authentication = authenticationManager.authenticate(authenticationToken);
    } catch (AuthenticationException ex) {
      String msg = "Invalid username or password!";
      log.error(msg);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(AuthenticationResponse.error(msg.toCharArray()));
    }
    UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();
    char[] accessToken = JWTUtil.generateToken(authenticatedUser);
    return ResponseEntity.status(HttpStatus.OK)
      .body(AuthenticationResponse.successToken1(accessToken));
  }

}
