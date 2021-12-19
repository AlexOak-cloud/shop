package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import shop.app.appUtills.SqlQuery;
import shop.app.entity.Message;
import shop.app.entity.User;
import shop.app.repository.MessageRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@EnableJpaRepositories
public class MessageService implements SqlQuery {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public List<Message> getChat(User user) {
        List<Message> rtnList = new ArrayList<>();
        try (final Statement statement = dataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(getChatBySender,user.getId()));
            while (resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setContent(resultSet.getString(2));
message.setDate(LocalDateTime.parse(resultSet.getDate(3)));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Message getById(int id) {
        return messageRepository.getById(id);
    }

    public void deleteById(int id) {
        messageRepository.deleteById(id);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }

    public List<Message> sortedListByDate(List<Message> list) {
        list.sort(Comparator.comparing(Message::getDate));
        Collections.reverse(list);
        return list;
    }
}
