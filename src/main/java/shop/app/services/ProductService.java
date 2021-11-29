package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import shop.app.entity.Product;
import shop.app.repository.ProductRepository;
import shop.app.entity.User;

import java.time.LocalDateTime;

@Service
@EnableJpaRepositories
public class ProductService {

    @Autowired
    private User user;

    @Autowired
    private ProductRepository productRepository;

    public boolean saveProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return false;
        }
        product.setDate(LocalDateTime.now());
        product.setUser(user);
        productRepository.save(product);
        return true;
    }
}
