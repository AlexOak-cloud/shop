package shop.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import shop.app.entity.Transaction;
import shop.app.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

public class TransactionServices {

    private final TransactionRepository repository;

    @Autowired
    public TransactionServices(TransactionRepository repository) {
        this.repository = repository;
    }


    public List<Transaction> findAll(){
        return repository.findAll();
    }


    public Transaction getById(Integer id){
        return repository.getById(id);
    }


    public <S extends Transaction> S save(S entity){
        return repository.save(entity);
    }

    public Optional<Transaction> findById(Integer id){
        return repository.findById(id);
    }


    public boolean existsById(Integer id){
        return repository.existsById(id);
    }


    public void deleteById(Integer id){
        repository.deleteById(id);
    }


    public void delete(Transaction entity){
        repository.delete(entity);
    }
}
