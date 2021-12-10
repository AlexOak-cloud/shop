package shop.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import shop.app.entity.products.Product;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableJpaRepositories
@Component
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username",unique = true)
    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 3,max = 10, message = "Длинна поля должна быть от 3 до 10 элемнтов ")
    private String username;
    @Column(name = "password")
    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 5, message = "Длинна поля должна быть от 5-x элементов ")
    private String password;
    @Transient
    private String checkPassword;
    @Column(name = "name")
    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 3, max = 10, message = "Длинна поля должна быть от 3 до 10 элементов")
    private String name;
    @Column(name = "secondName")
    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 3, max = 10, message = "Длинна поля должна быть от 3 до 10 элементов")
    private String secondName;
    @Column(name = "phone_number")
    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 7, max = 12, message = "Длинна поля должна быть от 7 до 12 элементов")
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
