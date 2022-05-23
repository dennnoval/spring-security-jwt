package dennnoval.springsecurityjwt.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JWTUtil {

  // Hard-coded
  private static final String secretKey = "vW0QGAQgmsc3Vo4npR09Dp1upNTw2fc9Ts0sxTeZhVI=";
  private static final String issuer = "http://localhost:8080";
  private static final Algorithm algorithm = Algorithm.HMAC512(secretKey);
  private static final long oneMin = 1000 * 60;
  private static final Date expired = new Date(System.currentTimeMillis() + (1000 * 300)); // 300 secs

  public static char[] generateToken(UserDetails user) {
    char[] token = null;
    try {
      token = (JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(expired)
        .withIssuer(issuer)
        .withClaim("role", user.getAuthorities().stream().findFirst().get().toString())
        .sign(algorithm))
        .toCharArray();
    }	catch (JWTCreationException ex) {
      //Invalid Signing configuration / Couldn't convert Claims.
      log.error(ex.getLocalizedMessage());
    }
    return token;
  }

  public static DecodedJWT verifyToken(char[] jwt) throws JWTDecodeException {
    JWTVerifier verifier = JWT.require(algorithm)
      .withIssuer(issuer)
      .build();
    DecodedJWT decodedJWT = verifier.verify(String.valueOf(jwt));
    return decodedJWT;
  }

  public static char[] refreshToken(UserDetails user) {
    return generateToken(user);
  }

}
