package shop.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_msg")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "isRead")
    private boolean isRead;
    @Column(name ="date_msg")
    private LocalDateTime dateTime;
    @Column(name = "sender")
    private int senderId;
    @Column(name = "recipient")
    private int recipientId;

    /**
     * CREATE TABLE t_msg(id INT AUTO_INCREMENT PRIMARY KEY,
     *                    content VARCHAR(1000) NOT NULL ,
     *                    isRead BOOLEAN DEFAULT FALSE,
     *                    date_msg DATETIME DEFAULT NULL,
     *                    sender INT,
     *                    recepient INT,
     *                    FOREIGN KEY (sender) REFERENCES usr(id)
     *                    ON DELETE CASCADE ON UPDATE CASCADE,
     *                    FOREIGN KEY (recepient) REFERENCES usr(id)
     *                    ON DELETE CASCADE ON UPDATE CASCADE)
     *                    ENGINE=innodb
     *
     */
}
