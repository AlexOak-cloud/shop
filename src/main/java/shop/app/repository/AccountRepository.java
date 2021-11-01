package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import shop.app.entity.Account;
import shop.app.entity.Person;

import java.util.List;
import java.util.Optional;

@Component
public interface AccountRepository extends JpaRepository<Account, Integer> {


    @Override
    List<Account> findAll();

    @Override
    Account getById(Integer integer);

    @Override
    <S extends Account> S save(S entity);

    @Override
    Optional<Account> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Account entity);
}
