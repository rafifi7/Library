package com.revature.controllers;

import com.revature.models.Book;
import com.revature.services.BookService;
import io.javalin.http.Handler;
import org.postgresql.util.PSQLException;

import java.util.ArrayList;

public class BookController {

    BookService bs = new BookService();

    public Handler getBooksHandler = ctx -> {
        ArrayList<Book> books = bs.getBooks();

        ctx.json(books);

        ctx.status(200);
    };

    public Handler checkoutBookHandler = ctx -> {
        Book book = ctx.bodyAsClass(Book.class);

        try {
            Book checkoutBook = bs.checkoutBook(book);
            ctx.status(201); //request has been fulfilled
            ctx.json(checkoutBook);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown while trying to checkout book");
        }
    };

    public Handler returnBookHandler = ctx -> {
        Book book = ctx.bodyAsClass(Book.class);

        try {
            Book returnBook = bs.returnBook(book);
            ctx.status(201); //request has been fulfilled
            ctx.json(returnBook);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown while trying to return book");
        }
    };


    public Handler insertBookHandler = ctx -> {
        Book book = ctx.bodyAsClass(Book.class);

        try {
            Book insertedBook = bs.insertBook(book);
            ctx.status(201); //request has been fulfilled
            ctx.json(insertedBook);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown while trying to insert book");
        }
    };

    public Handler deleteBookHandler = ctx -> {
        Book book = ctx.bodyAsClass(Book.class);

        try {
            Book deletedBook = bs.deleteBook(book);
            ctx.status(201);
            ctx.json(deletedBook);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown while trying to delete book");
        }
    };


}
