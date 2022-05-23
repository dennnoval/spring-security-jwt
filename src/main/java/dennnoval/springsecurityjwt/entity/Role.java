package dennnoval.springsecurityjwt.entity;

public enum Role {
  ADMIN,
  USER;

  public static Role getValueOf(String role) {
    if (role.equals(Role.ADMIN.name())) {
      return Role.ADMIN;
    }
    return Role.USER;
  }
}
