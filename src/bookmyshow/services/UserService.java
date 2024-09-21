package bookmyshow.services;

import java.util.HashMap;
import java.util.Map;

import bookmyshow.models.User;

public class UserService {
    private final Map<String, User> userMap;

    public UserService() {
        userMap = new HashMap<>();
    }

     public User findById(String id) {
        return userMap.get(id);
    }

    public User addUser(String name) {
        User user = new User(name);
        userMap.put(name, user);
        return user;
    }

}
