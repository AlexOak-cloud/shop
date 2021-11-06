package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.app.entity.User;
import shop.app.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll(){
        return repository.findAll();
    }


    public User getById(Integer id){
        return repository.getById(id);
    }


    public <S extends User> S save(S entity){
        return repository.save(entity);
    }

    public Optional<User> findById(Integer id){
        return repository.findById(id);
    }


    public boolean existsById(Integer id){
        return repository.existsById(id);
    }


    public void deleteById(Integer id){
        repository.deleteById(id);
    }


    public void delete(User entity){
        repository.delete(entity);
    }

}
