package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.app.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
