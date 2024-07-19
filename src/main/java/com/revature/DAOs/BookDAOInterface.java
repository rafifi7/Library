package com.revature.DAOs;

import com.revature.models.Book;
import com.revature.models.User;

import java.util.ArrayList;

public interface BookDAOInterface {


    // Select all items from the items table
    ArrayList<Book> getBooks();

    // Insert a new item into the items table
    Book insertBook(Book book);

    // Delete an item from the items table
    Book deleteBook(Book book);

    Book checkoutBook(Book book);

    Book returnBook(Book book);

}
