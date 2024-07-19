package com.revature.DAOs;

import com.revature.models.Book;
import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    // Insert a new user into the users table
    User insertUser(User user);

    // Update a user in the users table
    User updateUser(User user);

    // Select all items that belong to a certain user (find items by user id)
    ArrayList<Book> getBooksByUserId(int user_id);

    // Find all users
    ArrayList<User> getUsers();

}
