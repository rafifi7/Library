package com.revature;


import com.revature.controllers.BookController;
import com.revature.controllers.UserController;
import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {

        /* This is a "try with resources" block
        A resource is opened up within the parentheses of the try block
        If the resource is successfully created, the rest of the try block runs
        A big benefit of this is that the resource will close after the try block finishes
        This is helpful for code cleanup and preventing memory leaks */
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL :)");
        } catch(SQLException e){
            e.printStackTrace(); //tell us what went wrong
            System.out.println("connection failed :(");
        }

        //Typical Javalin setup syntax


        var app = Javalin.create(/*any extra configs would go here*/)
                .start(3000)
                .get("/", ctx -> ctx.result("Hello Javalin!")); //callable resource just for fun



        /*We need .start() to start our Javalin server on port 3000
         You can choose any port, I chose 3000 because probably nothing is running on it
         a port is like a parking space for an application, where messages, etc. can find it*/

        BookController bc = new BookController();
        UserController uc = new UserController();

        //GET REQS
        app.get("/books", bc.getBooksHandler);

        app.get("/users", uc.getUsersHandler);

        app.get("users/{user_id}", uc.getBooksByUserIdHandler);

        //POST REQS

        app.post("/books", bc.insertBookHandler);

        app.post("/users", uc.insertUserHandler);

        //UPDATE REQS

        app.patch("/users", uc.updateUserHandler);

        //Checkout
        app.patch("/books/checkout", bc.checkoutBookHandler);
        //Return
        app.patch("/books/return", bc.returnBookHandler);

        //DELETE REQS
        app.delete("/books", bc.deleteBookHandler);


    }

}
