package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import shop.app.appUtills.SqlQuery;
import shop.app.entity.Message;
import shop.app.repository.MessageRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@EnableJpaRepositories
public class MessageService implements SqlQuery {

    @Autowired
    private MessageRepository repository;

    public void save(Message message) {
        repository.save(message);
    }

    public List<Message> getAll() {
        return repository.findAll();
    }

    public Message getById(int id) {
        return repository.getById(id);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void delete(Message message) {
        repository.delete(message);
    }

    public List<Message> sortedListByDate(List<Message> list) {
        list.sort(Comparator.comparing(Message::getDate));
        Collections.reverse(list);
        return list;
    }
}
