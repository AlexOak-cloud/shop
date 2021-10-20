package shop.app.repos;


import org.springframework.data.repository.CrudRepository;
import shop.app.entity.Person;

public interface MyCrudRepository extends CrudRepository<Person,Long> {


}
