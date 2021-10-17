package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {



}
