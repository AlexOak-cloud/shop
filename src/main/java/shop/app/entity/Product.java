package shop.app.entity;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name ="product")
@Getter
@Setter
@NoArgsConstructor
@EnableJpaRepositories
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private int price;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    /**
     * CREATE TABLE product (id INT PRIMARY KEY AUTO_INCREMENT,
     *                       person_id INT ,
     *                       NAME VARCHAR(255) NOT NULL,
     *                       description VARCHAR(1000),
     * 						 price INT CHECK (price>0),
     * 						 FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
     * 						 ON UPDATE CASCADE )
     * 						 ENGINE=INNODB
     */

    public Product(String name, String description, int price, Person person) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id
                && price == product.price
                && Objects.equals(name, product.name)
                && Objects.equals(description, product.description)
                && Objects.equals(person, product.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, person);
    }
}
