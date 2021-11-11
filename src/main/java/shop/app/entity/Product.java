package shop.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = " price")
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
