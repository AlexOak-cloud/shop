package shop.app.entity;


import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "client")
@EnableJpaRepositories
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "adres")
    private String adres;
    @Column(name = "orderTime")
    private LocalDateTime orderTime;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adres='" + adres + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name)
                && Objects.equals(adres, client.adres) && Objects.equals(orderTime, client.orderTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, adres, orderTime);
    }

    public Client() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdres() {
        return adres;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public Client(String name, String adres, LocalDateTime orderTime) {
        this.name = name;
        this.adres = adres;
        this.orderTime = orderTime;
    }

    @Bean
    public static Client action() {
        return new Client();
    }
}
