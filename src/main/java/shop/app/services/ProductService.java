package shop.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import shop.app.entity.Product;
import shop.app.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public List<Product> findAll(){
        return repository.findAll();
    }


    public Product getById(Integer id){
        return repository.getById(id);
    }


    public <S extends Product> S save(S entity){
        return repository.save(entity);
    }

    public Optional<Product> findById(Integer id){
        return repository.findById(id);
    }


    public boolean existsById(Integer id){
        return repository.existsById(id);
    }


    public void deleteById(Integer id){
        repository.deleteById(id);
    }


    public void delete(Product entity){
        repository.delete(entity);
    }
}
