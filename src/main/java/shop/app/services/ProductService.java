package shop.app.services;


import org.springframework.stereotype.Component;
import shop.app.entity.User;
import shop.app.repository.ProductRepository;

import java.util.List;

@Component
public interface ProductService extends ProductRepository {
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
