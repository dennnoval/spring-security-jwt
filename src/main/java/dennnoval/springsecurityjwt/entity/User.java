package dennnoval.springsecurityjwt.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Users")
public class User implements Serializable {

  @Id
  @Column(updatable = false, length = 16)
  private String username;
  @Column(length = 64)
  private char[] password;
  @Column(length = 32)
  private String email;
  @Enumerated(EnumType.STRING)
  private Role role;
  @Column(name = "created_at")
  private final Date createdAt = new Date(System.currentTimeMillis());
  @Column(name = "last_request")
  private Date lastRequest;

  public User(String username, char[] password, String email, Role role) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
  }

  public User(String username, char[] password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = Role.USER;
  }

}
