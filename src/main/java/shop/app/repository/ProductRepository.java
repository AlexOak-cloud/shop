package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Override
    List<Product> findAll();

    @Override
    Product getById(Integer integer);

    @Override
    <S extends Product> S save(S entity);

    @Override
    Optional<Product> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Product entity);
}
