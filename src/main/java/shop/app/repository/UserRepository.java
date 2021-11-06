package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import shop.app.entity.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {


    @Override
    List<User> findAll();

    @Override
    User getById(Integer integer);

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(User entity);
}
