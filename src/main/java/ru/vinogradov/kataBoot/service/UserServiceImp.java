package ru.vinogradov.kataBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vinogradov.kataBoot.DAO.UserDAO;
import ru.vinogradov.kataBoot.model.Role;
import ru.vinogradov.kataBoot.model.User;
import ru.vinogradov.kataBoot.repositories.UserRepository;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername (String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User `%s` not found", name));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthority (Collection<Role> roles) {
        return roles.stream().map(r ->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void add (User user) {
        userDAO.add(user);
    }

    @Transactional
    public void update (Long id, User updatedUser) {
        userDAO.update(id, updatedUser);
    }

    @Transactional
    public void delete (Long id) {
        userDAO.delete(id);
    }

    @Transactional
    public List<User> getAll () {
        return userDAO.getAll();
    }

    @Transactional
    public User show (Long id) {
        return userDAO.show(id);
    }

}
