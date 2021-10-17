package shop.app;

import org.hibernate.Transaction;
import org.springframework.context.annotation.Bean;
import shop.app.entity.Product;

public class Test {

   


    @Bean
    public static Test action(){
        return new Test();
    }

}
