package com.revature.controllers;

import com.revature.models.Book;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class UserController {

    UserService us = new UserService();

    public Handler getUsersHandler = ctx -> {
        ArrayList<User> users = us.getUsers();
        ctx.json(users);
        ctx.status(200);
    };


    public Handler getBooksByUserIdHandler = ctx -> {
        int user_id = Integer.parseInt(ctx.pathParam("user_id"));

        try {
            ArrayList<Book> books = us.getBooksByUserId(user_id);
            ctx.status(201);
            ctx.json(books);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown while trying to get books by user id");
        }
    };

    public Handler insertUserHandler = ctx -> {
        User user = ctx.bodyAsClass(User.class);

        try {
            User insertedUser = us.insertUser(user);
            ctx.status(201); //request has been fulfilled
            ctx.json(insertedUser);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown while trying to insert user");
        }
    };

    public Handler updateUserHandler = ctx -> {
        User user = ctx.bodyAsClass(User.class);

        try {
            User updatedUser = us.updateUser(user);
            ctx.status(201);
            ctx.json(updatedUser);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown while trying to update user");
        }
    };



}
