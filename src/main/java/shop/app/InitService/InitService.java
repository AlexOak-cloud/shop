package shop.app.InitService;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import shop.app.repository.ProductRepository;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Profile("init")
@Component
public class InitService {

    
    private final ProductRepository;

    @PostConstruct
    public void init(){

    }
}
