package com.revature.services;

import com.revature.DAOs.BookDAO;
import com.revature.models.Book;
import com.revature.models.User;

import java.util.ArrayList;

public class BookService {
    BookDAO bookDAO = new BookDAO();

    public ArrayList<Book> getBooks() {
        return bookDAO.getBooks();
    }

    public Book insertBook(Book book) throws IllegalArgumentException {
        if (book.getId() < 0) {
            throw new IllegalArgumentException("User Id cannot be less than 0!");
        }

        if (book.getAuthor() == null || book.getAuthor().equals("")) {
            throw new IllegalArgumentException("Book Author cannot be empty!");
        }

        if (book.getTitle() == null || book.getTitle().equals("")) {
            throw new IllegalArgumentException("Book Title cannot be empty!");
        }

        bookDAO.insertBook(book);

        return book;
    }


    public Book deleteBook(Book book) throws IllegalArgumentException {
        if (book.getId() < 1) {
            throw new IllegalArgumentException("Book that you are trying to delete cannot have id less than 1!");
        }

        return bookDAO.deleteBook(book);
    }


    public Book checkoutBook(Book book) throws IllegalArgumentException {
        if (book.isChecked_out()) {
            throw new IllegalArgumentException("This book is already checked out!");
        }

        if (book.getId() < 1) {
            throw  new IllegalArgumentException("Book that you are trying to checkout does not exist");
        }

        return bookDAO.checkoutBook(book);
    }


    public Book returnBook (Book book) throws IllegalArgumentException {
        if (!book.isChecked_out()) {
            throw new IllegalArgumentException("This book cannot be returned because it is not checked out!");
        }

        if (book.getId() < 1) {
            throw new IllegalArgumentException("Book that you are trying to return does not exist");
        }

        return bookDAO.returnBook(book);
    }



}
