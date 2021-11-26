package shop.app.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Data
@Table(name = "user_roles")
public class Roles implements GrantedAuthority {

    private static final String USER = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private byte id;
    @Column(name = "roles")
    private String role;

    public Roles(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

}
