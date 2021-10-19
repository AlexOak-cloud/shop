package shop.app.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import shop.app.entity.Product;

public interface MyRepo  extends JpaRepository<Product,Long> {



}
