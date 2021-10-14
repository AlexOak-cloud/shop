package shop.app.Entity;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public abstract class AbstractProduct {

    private long id;
    private String name;
    private int price;
}
