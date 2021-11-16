package shop.app.services;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import shop.app.entity.User;
import shop.app.repository.UserRepository;

import java.util.Optional;

@Component
@EnableJpaRepositories
@Service
public interface UserService extends UserRepository {

    @Override
    Optional<User> findByUsername(String username);
}
