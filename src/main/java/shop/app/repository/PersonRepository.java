package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.entity.Person;
import shop.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Override
    List<Person> findAll();

    @Override
    Person getById(Integer integer);

    @Override
    <S extends Person> S save(S entity);

    @Override
    Optional<Person> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Person entity);
}
