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
import java.util.List;

@Repository
public class DialogRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:jdbc/schema.sql")
                .addScript("classpath:jdbc/test-data.sql").build();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createChat(User recipientUser) {
        jdbcTemplate.execute(
                "CREATE TABLE dialog_" + userService.getAuthUser() + "_" + recipientUser.getId() +
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

    public void saveMessage(String message, User recipientUser, LocalDateTime date,boolean isRead){
        jdbcTemplate.execute("insert into " +
                "dialog_"+userService.getAuthUser() + "_" + recipientUser.getId() +
                "(recipientUser,message,date_msg,isRead) values " +
                "(" + recipientUser.getId() +"," + message +"," + date + "," + isRead + ")");
    }

    public List<Dialog> getAll(){
        !!!!!


    }



}