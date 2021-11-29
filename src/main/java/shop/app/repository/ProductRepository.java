package shop.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
