package course.service;

import course.entities.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> getAll();

}
