package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.entity.Product;
import shop.app.entity.User;

import java.util.List;

public interface ProductRepository<T extends Product> extends JpaRepository<Product, Integer> {


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
