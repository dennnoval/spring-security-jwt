package dennnoval.springsecurityjwt.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import dennnoval.springsecurityjwt.entity.Role;
import dennnoval.springsecurityjwt.utility.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Log4j2
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      char[] jwt = authorizationHeader.substring("Bearer ".length()).toCharArray();
      try {
        DecodedJWT decodedJWT = JWTUtil.verifyToken(jwt);
        String username = decodedJWT.getSubject();
        Role role = decodedJWT.getClaim("role").as(Role.class);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        AnonymousAuthenticationToken authenticationToken = new AnonymousAuthenticationToken(
          "Authorization", username, authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      } catch (JWTVerificationException ex) {
        String msg = ex.getLocalizedMessage();
        if (msg.contains("The string") || msg.contains("base 64"))
          msg = "Invalid token format!";
        log.error(msg);
        String json = "{\"error\": \"" + msg + "\"}";
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

}
