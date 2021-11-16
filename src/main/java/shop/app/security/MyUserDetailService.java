package shop.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import shop.app.entity.User;
import shop.app.services.UserService;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService repository;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User byName = repository.findByUsername(name).orElse(null);
        return new UserDetailsManager(byName);
    }
}
