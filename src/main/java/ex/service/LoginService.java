package ex.service;

import ex.entity.Login;
import ex.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LoginService implements UserDetailsService{

    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> result = loginRepository.findByUsername(username);
        if(result.isPresent()) {
            Login ll  = result.get();
            return User.builder().username(ll.getUsername()).password(ll.getPassword()).roles(getRoles(ll)).build();
        }else {
            throw new UsernameNotFoundException(username);
        }

    }

    public String[] getRoles(Login ll) {
        if(ll.getRole()==null) {
            return new String[] {"USER"};
        }else {
            return ll.getRole().split(",");
        }
    }

    public String createAccount(Login ll) {
        Optional<Login> result = loginRepository.findByUsername(ll.getUsername());
        if(result.isPresent()) {
            return "User already exists";
        }else {
            loginRepository.save(ll);
            return "Account created successfully";
        }

    }
    /*
    public List<Login> searchUsers(String keyword) {
        return loginRepository.findByUsernameContaining(keyword);
    }
    */
    public List<Login> loggedInUsers(int id) {
        return loginRepository.findLoginById(id);		// custom methods
    }

}