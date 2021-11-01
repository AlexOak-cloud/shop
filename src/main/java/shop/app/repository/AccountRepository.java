package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.entity.Account;
import shop.app.entity.Person;

import java.util.List;
import java.util.Optional;

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
