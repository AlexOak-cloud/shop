package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import shop.app.entity.Transaction;

import java.util.List;
import java.util.Optional;

@Component
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Override
    List<Transaction> findAll();

    @Override
    Transaction getById(Integer integer);

    @Override
    <S extends Transaction> S save(S entity);

    @Override
    Optional<Transaction> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Transaction entity);
}
