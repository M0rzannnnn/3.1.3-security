package ru.vinogradov.kataBoot.service;



import ru.vinogradov.kataBoot.model.User;

import java.util.List;

public interface UserService {
    void add (User user);

    void update (User user);

    void delete (Long id);

    List<User> getAll ();

    User show (Long id);

    User findByUsername (String name);
}
