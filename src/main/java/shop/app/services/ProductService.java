package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import shop.app.appUtills.SqlQuery;
import shop.app.entity.User;
import shop.app.entity.products.Product;
import shop.app.repository.ProductRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@EnableJpaRepositories
public class ProductService implements SqlQuery {

    @Autowired
    private ProductRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    private DataSource dataSource;


    public boolean save(Product product) {
        product.setUser(userService.getAuthUser());
        product.setDate(LocalDate.now());
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

    public List<Product> getAll() {
        return repository.findAll();
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public List<Product> getAllByUser(User user) {
        try {
            List<Product> list = new ArrayList<>();
            final Connection connection = dataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    String.format(SqlQuery.getAllProductByUser, user.getId()));
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(Integer.parseInt(resultSet.getString(1)));
                product.setCategory(resultSet.getString(2));
                product.setDate(LocalDate.parse(resultSet.getString(3)));
                product.setDescription(resultSet.getString(4));
                product.setName(resultSet.getString(5));
                product.setPrice(Integer.parseInt(resultSet.getString(6)));
                product.setUser(user);
                if(getById(product.getId()) != null){
                    continue;
                }
                list.add(product);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }
}
