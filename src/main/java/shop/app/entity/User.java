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
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "user_roles")
    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<Roles> roles;



    /**
     * CREATE TABLE users_tbl(id INT AUTO_INCREMENT PRIMARY KEY,
     *                        username VARCHAR(255) NOT NULL,
     *                        PASSWORD VARCHAR(255) NOT NULL,
     *                        phone_number VARCHAR(255) NOT NULL,
     *                        role_id INT ,
     *                        FOREIGN KEY (role_id) REFERENCES user_roles(id)
     *                        oN DELETE CASCADE oN UPDATE CASCADE )
     *                        ENGINE = INNODB
     *
     */
}
