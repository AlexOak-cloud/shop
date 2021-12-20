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
import java.time.format.DateTimeFormatter;
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

    public List<Message> getChat(User senderUser, User recipientUser) {
        List<Message> rtnList = new ArrayList<>();
        try (final Statement statement = dataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(
                    getChatByRecipient, recipientUser.getId(), senderUser.getId()));
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setContent(resultSet.getString(2));
                message.setDate(LocalDateTime.parse(resultSet.getString(3),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
                message.setRead(resultSet.getBoolean(4));
                message.setRecipientUser(userService.getById(resultSet.getInt(5)));
                message.setSenderUser(userService.getById(resultSet.getInt(6)));
                rtnList.add(message);
            }
            return sortedListByDate(rtnList);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<String> formatChat(List<Message> list) {
        List<String> rtnList = new ArrayList<>();
        for (Message tmp : list) {
            if (tmp.getSenderUser().getId() == userService.getAuthUser().getId()) {
                rtnList.add("Вы: \n" + tmp.getContent() + "\n" + tmp.getDate());
            } else {
                rtnList.add(tmp.getSenderUser().getName() + ": \n" + tmp.getContent()+ "\n" + tmp.getDate());
            }
        }
        return rtnList;
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
