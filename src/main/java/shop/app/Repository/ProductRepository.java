package shop.app.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import shop.app.entity.Product;

@Component
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



}
