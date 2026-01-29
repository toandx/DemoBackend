package toandx.render.demoBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import toandx.render.demoBackend.entity.User;
import toandx.render.demoBackend.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public User loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void createUser(String username,String originalPassword,String role)
    {
        String password= BCrypt.hashpw(originalPassword,BCrypt.gensalt(10));
        userRepository.save(new User(username,password,authorityService.getAuthority(role)));
    }

    public List<User> findAllUser()
    {
        return userRepository.findAll();
    }

    public boolean deleteUser(String username)
    {
        if (!userRepository.existsById(username))
            return(false);
        userRepository.deleteById(username);
        return true;
    }
}

