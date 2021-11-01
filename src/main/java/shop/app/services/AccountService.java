package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.app.entity.Account;
import shop.app.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll(){
        return repository.findAll();
    }


    public Account getById(Integer id){
        return repository.getById(id);
    }


    public <S extends Account> S save(S entity){
        return repository.save(entity);
    }

    public Optional<Account> findById(Integer id){
        return repository.findById(id);
    }


    public boolean existsById(Integer id){
        return repository.existsById(id);
    }


    public void deleteById(Integer id){
        repository.deleteById(id);
    }


    public void delete(Account entity){
        repository.delete(entity);
    }

}
