package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import shop.app.appUtills.SqlQuery;
import shop.app.entity.User;
import shop.app.entity.products.Product;
import shop.app.entity.products.ProductCategories;
import shop.app.repository.ProductRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<Product> sortedListByDate(List<Product> list) {
        list.sort(Comparator.comparing(Product::getDate));
        Collections.reverse(list);
        return list;
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
                list.add(product);
            }
            connection.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Product> getAllByCategory(ProductCategories categories) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            final ResultSet resultSet = connection.
                    createStatement().
                    executeQuery(String.format(SqlQuery.getAllCategory, categories.toString()));
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(Integer.parseInt(resultSet.getString(1)));
                product.setCategory(resultSet.getString(2));
                product.setDate(LocalDate.parse(resultSet.getString(3)));
                product.setDescription(resultSet.getString(4));
                product.setName(resultSet.getString(5));
                product.setPrice(Integer.parseInt(resultSet.getString(6)));
                product.setUser(userService.getAuthUser());
                products.add(product);
            }
            connection.close();
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.close();
            return Collections.emptyList();
        }
    }
}
