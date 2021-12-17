package shop.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_msg")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "message")
    @NotNull(message = "Сообщение не может быть пустым")
    private String message;
    @NotNull
    @Column(name = "date_msg")
    private LocalDateTime date;
    @Column(name = "is_read")
    @NotNull
    private boolean isRead;
    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private User senderUser;
    @ManyToOne
    @JoinColumn(name = "recipient_user_id")
    private User recipientUser;

    /**
     CREATE TABLE usr_message(id INT PRIMARY KEY AUTO_INCREMENT,
     sender_user_id INT NOT NULL ,
     recipient_user_id int NOT NULL,
     date_msg DATE DEFAULT NULL,
     message VARCHAR(1000) DEFAULT NULL,
     is_read BOOLEAN DEFAULT FALSE,
     FOREIGN KEY (sender_user_id) REFERENCES usr(id)
     ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY (recipient_user_id) REFERENCES usr(id)
     ON DELETE CASCADE ON UPDATE CASCADE)
     ENGINE=INNODB;
     */
}
