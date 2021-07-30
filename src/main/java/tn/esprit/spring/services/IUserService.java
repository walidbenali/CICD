package tn.esprit.spring.services;

import tn.esprit.spring.entities.User;

import java.util.List;

public interface IUserService {

    List<User> retrieveAllUsers();

    User addUser(User u);

    void deleteUser(String id);

    User updateUser(User u);

    User retrieveUser(String id);

} 
