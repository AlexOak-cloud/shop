package shop.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_tbl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username")
    private String  username;
    @Column(name="password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated
    @Column(name = "roles")
    private Set<UserRole> roles;
}
