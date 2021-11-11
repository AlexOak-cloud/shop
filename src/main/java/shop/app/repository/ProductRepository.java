package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.entity.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<User, Integer> {


    @Override
    List<User> findAll();

    @Override
    User getById(Integer integer);

    @Override
    <S extends User> S save(S entity);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(User entity);
}
