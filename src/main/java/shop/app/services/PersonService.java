package shop.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.app.entity.Account;
import shop.app.entity.Person;
import shop.app.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Component
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll(){
        return repository.findAll();
    }


    public Person getById(Integer id){
        return repository.getById(id);
    }


    public <S extends Person> S save(S entity){
        return repository.save(entity);
    }

    public Optional<Person> findById(Integer id){
        return repository.findById(id);
    }


    public boolean existsById(Integer id){
        return repository.existsById(id);
    }


    public void deleteById(Integer id){
        repository.deleteById(id);
    }


    public void delete(Person entity){
        repository.delete(entity);
    }
}
