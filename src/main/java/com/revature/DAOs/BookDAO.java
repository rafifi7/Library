package com.revature.DAOs;

import com.revature.models.Book;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO implements BookDAOInterface {

    @Override
    public ArrayList<Book> getBooks() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books";

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            ArrayList<Book> books = new ArrayList<>();

            while (rs.next()) {
                Book b = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("user_id"),
                        rs.getBoolean("checked_out")
                );
                books.add(b);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book insertBook(Book book) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql;
            if (book.getId() != 0) {
                sql = "INSERT INTO books (id, title, author, user_id, checked_out) VALUES (?,?,?,?,?)";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, book.getId());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getAuthor());
                ps.setInt(4, book.getUser_id());
                ps.setBoolean(5, book.isChecked_out());

                ps.executeUpdate();

            } else {
                sql = "INSERT INTO books (title, author, user_id, checked_out) VALUES (?,?,?,?)";

                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setInt(3, book.getUser_id());
                ps.setBoolean(4, book.isChecked_out());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            book.setId(rs.getInt(1));
                        }
                    }
                }
            }
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert Book");
        }
        return null;
    }

//    public Book getBookById(int book_id) {
//        try (Connection conn = ConnectionUtil.getConnection()) {
//            String sql = "SELECT * FROM books WHERE id = ?";
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, book_id);
//
//            ResultSet rs = ps.executeQuery();  // Execute the query
//
//            if (rs.next()) {
//                return new Book(
//                        rs.getInt("id"),
//                        rs.getString("title"),
//                        rs.getString("author"),
//                        rs.getInt("user_id"),
//                        rs.getBoolean("checked_out")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public Book checkoutBook(Book book) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE books SET user_id = ?, checked_out = true WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, book.getUser_id());
            ps.setInt(2, book.getId());

            ps.executeUpdate();

            book.setChecked_out(true);

            return book;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to checkout book");
        }

        return null;
    }

    @Override
    public Book returnBook(Book book) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE books SET user_id = ?, checked_out = false WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, 1);
            ps.setInt(2, book.getId());

            ps.executeUpdate();

            book.setChecked_out(false);
            book.setUser_id(1);

            return book;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to checkout book");
        }
        return null;
    }



    @Override
    public Book deleteBook(Book book) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE from books where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book.getId());

            int deletedRows = ps.executeUpdate();

            if (deletedRows > 0) {
                return book;
            } else {
                System.out.println("No book found with this id");
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete book");
        }
        return null;
    }





}
