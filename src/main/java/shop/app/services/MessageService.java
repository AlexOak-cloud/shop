package shop.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import shop.app.entity.Message;
import shop.app.repository.MessagesRepositories;

import java.util.List;
import java.util.Optional;

@Service
@EnableJpaRepositories
public class MessageService {

    @Autowired
    private MessagesRepositories repositories;

    public List<Message> findAll() {
        return repositories.findAll();
    }


    public Message getById(Integer id) {
        return repositories.getById(id);
    }

    public <S extends Message> S save(S entity) {
        return repositories.save(entity);
    }


    public Optional<Message> findById(Integer integer) {
        return findById(integer);
    }


    public void deleteById(Integer id) {
        repositories.deleteById(id);
    }


    public void delete(Message entity) {
        repositories.delete(entity);
    }
}
