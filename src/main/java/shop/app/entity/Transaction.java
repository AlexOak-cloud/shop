package shop.app.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@EnableJpaRepositories
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "time_trn")
    private LocalDateTime time;

    /**
     * CREATE TABLE TRANSACTION (id INT PRIMARY KEY AUTO_INCREMENT,
     *                            person_id INT,
     *                            product_id INT ,
     *                            TIME_trn DATETIME,
     * 						      FOREIGN KEY (person_id) REFERENCES person(id)
     * 					     	  ON DELETE CASCADE ON UPDATE CASCADE ,
     * 							  FOREIGN KEY (product_id) REFERENCES product(id)
     * 							  ON DELETE CASCADE ON UPDATE CASCADE )
     * 							  ENGINE = INNODB
     */

    public Transaction(Person person, Product product, LocalDateTime time) {
        this.person = person;
        this.product = product;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id
                && Objects.equals(person, that.person)
                && Objects.equals(product, that.product)
                && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, product, time);
    }
}
