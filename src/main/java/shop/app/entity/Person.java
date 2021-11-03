package shop.app.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@EnableJpaRepositories
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    /**
     *  CREATE TABLE person( id INT PRIMARY KEY AUTO_INCREMENT,
     *                       account_id INT ,
     *                       NAME VARCHAR(255) NOT NULL,
     *                       phoneNumber VARCHAR(255) ,
     *                       FOREIGN KEY (account_id) REFERENCES account(id)
     *                       ON DELETE CASCADE ON UPDATE CASCADE)
     *                       ENGINE=INNODB;
     */


    public Person(String name, String phoneNumber, Account account) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id
                && Objects.equals(name, person.name)
                && Objects.equals(phoneNumber, person.phoneNumber)
                && Objects.equals(account, person.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, account);
    }
}
