package shop.app.repository;

import org.springframework.data.repository.CrudRepository;
import shop.app.entity.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {



}
