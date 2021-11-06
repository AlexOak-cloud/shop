package shop.app.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
@NoArgsConstructor
@EnableJpaRepositories

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)                  // |
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))     //  \  ?
    @Enumerated(EnumType.STRING)                                                           //  /
    private Set<Role> roles;                                                               // |

    /**
     * CREATE TABLE user_tbl (id INT PRIMARY KEY AUTO_INCREMENT,
     * login VARCHAR(255) NOT NULL,
     * PASSWORD VARCHAR(255) NOT NULL,
     * NAME VARCHAR(255) NOT NULL,
     * phoneNumber VARCHAR(255))
     */

    public User(String login, String password, String name, String phoneNumber, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(name, user.name)
                && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, phoneNumber, roles);
    }
}
