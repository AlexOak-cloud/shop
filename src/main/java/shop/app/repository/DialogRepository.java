package shop.app.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Repository;
import shop.app.entity.Dialog;
import shop.app.entity.User;
import shop.app.services.UserService;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class DialogRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createChat(User sender, User recipient) {
        jdbcTemplate.execute(
                "CREATE TABLE dialog_" + sender.getId() + "_" + recipient.getId() +
                        "(id INT AUTO_INCREMENT PRIMARY KEY," +
                        "recipientUser INT," +
                        "message VARCHAR(1000) NOT NULL," +
                        "date_msg DATETIME DEFAULT NULL," +
                        "isRead BOOLEAN DEFAULT FALSE," +
                        "FOREIGN KEY (senderUser) REFERENCES usr(id)" +
                        "ON DELETE CASCADE ON UPDATE CASCADE," +
                        "FOREIGN KEY (recipientUser) REFERENCES usr(id)" +
                        "ON DELETE CASCADE ON UPDATE CASCADE)" +
                        "ENGINE=innodb");
    }

    public void saveMessage(String message, User recipientUser, LocalDateTime date, boolean isRead) {
        jdbcTemplate.update("insert into " +
                "dialog_" + userService.getAuthUser() + "_" + recipientUser.getId() +
                "(recipientUse?,message,date_msg,isRead) values " +
                "(?,?,?,?)", recipientUser.getId(), message, date, isRead);
    }

    public List<Dialog> getAll(User sender, User recepient) {
       jdbcTemplate.


        return Collections.emptyList();
    }

}