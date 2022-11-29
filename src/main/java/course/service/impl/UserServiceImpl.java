package course.service.impl;

import course.entities.User;
import course.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // UserRepository repository;

    @Override
    public void add(User user) {

    }

    @Override
    public List<User> getAll() {
        List<User> users = Arrays.asList(
                new User(1L, "Maria", "maria@gmail.com", "99999999", "12345"),
                new User(1L, "Maria A", "maria@gmail.com", "99999999", "123456"),
                new User(1L, "Maria A B", "maria@gmail.com", "99999999", "1234567")
        );
        return users;
    }

}
