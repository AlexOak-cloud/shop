package shop.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import shop.app.entity.Message;

@Repository
@EnableJpaRepositories
public interface MessageRepository extends JpaRepository<Message,Integer> {

}
