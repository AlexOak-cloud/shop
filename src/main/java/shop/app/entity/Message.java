package shop.app.entity;


import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 1, max = 1000)
    @NotNull(message = "Сообщение не может быть пустым")
    private String message;
    @ManyToMany
    @JoinTable(name = "usr" ,joinColumns = @JoinColumn(name = "user_id"))
    private User user;
    @ManyToMany
    @JoinTable(name = "usr" ,joinColumns = @JoinColumn(name = "adress_id"))
    private User address;


}
