package shop.app.entity.products;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.app.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @Size(min = 2, max = 100)
    @NotNull(message = "Поле не может быть пустым")
    private String name;
    @Column(name = "description")
    @Size(min = 1, max = 1000)
    @NotNull(message = "Поле не может быть пустым")
    private String description;
    @Column(name = "price")
    @NotNull(message = "Поле не может быть пустым")
    private int price;
    @Column(name = "date")
    @NotNull
    private LocalDate date;
    @Column(name = "category")
    @NotNull
    private String category;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
