package shop.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name= "description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "date")
    private LocalDateTime date;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
