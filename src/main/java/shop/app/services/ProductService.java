package shop.app.services;


import org.springframework.stereotype.Component;
import shop.app.entity.Product;
import shop.app.entity.User;
import shop.app.repository.ProductRepository;

import java.util.List;

@Component
public interface ProductService extends ProductRepository<Product> {
    @Override
    List<Product> findAll();

    @Override
    Product getById(Integer integer);

    @Override
    <S extends Product> S save(S entity);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Product entity);
}
