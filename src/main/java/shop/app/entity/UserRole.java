package shop.app.entity;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

   public Role role;

    public UserRole(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }
}
