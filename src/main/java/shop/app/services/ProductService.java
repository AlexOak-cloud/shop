package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import shop.app.entity.products.Product;
import shop.app.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableJpaRepositories
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    UserService userService;


    public boolean save(Product product) {
        product.setUser(userService.getAuthUser());
        product.setDate(LocalDateTime.now());
        repository.save(product);
        return true;
    }

    public Product getById(int id) {
        final Product byId = repository.getById(id);
        if (byId == null) {
            return new Product();
        }
        return byId;
    }

    public List<Product> getAll(){
        return repository.findAll();
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

//    public List<Product> getAllByUser(User user){
//        try {
//            final Connection connection = dataSource.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement();
//
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }
//    }
}
