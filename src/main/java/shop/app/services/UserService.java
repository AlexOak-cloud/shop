package shop.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shop.app.entity.Role;
import shop.app.repository.UserRepository;
import shop.app.entity.User;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private EntityManager em;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(int id){
        final Optional<User> userById = userRepository.findById(id);
        return userById.orElse(new User());
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public boolean saveUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB != null){
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L,"ROLE_USER")));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public  boolean deleteUser(int userId){
        if(userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
