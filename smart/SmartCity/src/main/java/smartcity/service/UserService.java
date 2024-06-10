package ru.smartcity.service;


import ru.smartcity.model.User;

public interface UserService {

    void add(User user);

    User getByUsernameAndPassword(String username, String password);
    User get(long id);
    User update(User user);
    User updateLikes(User user);
    User delete(long id);
}
