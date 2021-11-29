package shop.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shop.app.entity.Role;
import shop.app.repository.RoleRepository;
import shop.app.repository.UserRepository;
import shop.app.entity.User;

import javax.persistence.EntityManager;
import java.net.SecureCacheResponse;
import java.security.Principal;
import java.security.ProtectionDomain;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(int id) {
        final Optional<User> userById = userRepository.findById(id);
        return userById.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        Set<Role> roles = new HashSet<>();
        final Role roleById = roleRepository.getById(1);
        roles.add(roleById);
        user.setRoles(roles);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(int userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public User getAuthUser() {
        Principal principal =
        return userRepository.findByUsername(principal.getName());
    }
}
