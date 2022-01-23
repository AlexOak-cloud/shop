package shop.app.services;


import org.springframework.beans.factory.annotation.Autowired;
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
public class MessageService implements SqlQuery {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    private static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean save(Message message) {
        if (message == null) {
            return false;
        }
        message.setDateTime(LocalDateTime.now());
        message.setSenderId(userService.getAuthUser().getId());
        messageRepository.save(message);
        return true;
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public Message getById(Integer id) {
        return messageRepository.getById(id);
    }

    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }

    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    public List<Message> getChat(int recipientId) {
        List<Message> rtnList = new ArrayList<>();
        int authUserId = userService.getAuthUser().getId();
        try (Statement statement = dataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format(
                            getChatByRecipient,
                            authUserId,
                            recipientId,
                            recipientId,
                            authUserId
                    ));
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setContent(resultSet.getString(2));
                message.setRead(true);
                message.setSenderId(resultSet.getInt(4));
                message.setRecipientId(resultSet.getInt(5));
                message.setDateTime(
                        LocalDateTime.parse(String.valueOf(resultSet.getString(6)),
                                dateTimeFormatter));
                rtnList.add(message);
            }
            return rtnList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Message> sortedListByDate(List<Message> list) {
        list.sort(Comparator.comparing(Message::getDateTime));
        return list;
    }

    public List<String> formatList(List<Message> list) {
        List<String> rtnList = new ArrayList<>();
        String exemple = null;
        for (Message tmp : list) {
            if(tmp.getSenderId() == userService.getAuthUser().getId()){
                rtnList.add("Вы:\n" + tmp.getContent() + "\n");
            } else {
                rtnList.add(userService.getById(tmp.getRecipientId()).getName() + ":\n" +
                        tmp.getContent() + "\n");
            }
        }
        return rtnList;
    }
}
