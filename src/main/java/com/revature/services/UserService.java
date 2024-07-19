package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.models.Book;
import com.revature.models.User;

import java.util.ArrayList;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public ArrayList<User> getUsers() {
        return userDAO.getUsers();
    }

    public ArrayList<Book> getBooksByUserId(int user_id) throws IllegalArgumentException {
        if (user_id < 1) {
            throw new IllegalArgumentException("User Id cannot be less than 1!");
        }

        return userDAO.getBooksByUserId(user_id);
    }

    public User insertUser(User user) {
        if (user.getId() < 0) {
            throw new IllegalArgumentException("User Id cannot be less than 0!");
        }
        if (user.getName() == null || user.getName().equals("")) {
            throw new IllegalArgumentException("User Name cannot be empty!");
        }

        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new IllegalArgumentException("User Email cannot be empty!");
        }

        return userDAO.insertUser(user);
    }


    public User updateUser(User user) {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User Id cannot be less than 1!");
        }
        if (user.getName() == null || user.getName().equals("")) {
            throw new IllegalArgumentException("User Name cannot be empty!");
        }

        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new IllegalArgumentException("User Email cannot be empty!");
        }

        return userDAO.updateUser(user);
    }


}
