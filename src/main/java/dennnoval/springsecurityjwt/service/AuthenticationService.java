package dennnoval.springsecurityjwt.service;

import dennnoval.springsecurityjwt.entity.User;
import dennnoval.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private UserRepository repo;

  /*public User loadByUsernameAndPassword(String username, char[] password) throws AuthenticationException {
    User user = repo.findByUsernameAndPassword(username, password);
    return user;
  }*/

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repo.findById(username).get();
    String uname = user.getUsername();
    String pass = String.valueOf(user.getPassword());
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));

    return new org.springframework.security.core.userdetails.User(uname, pass, grantedAuthorities);
  }

}
