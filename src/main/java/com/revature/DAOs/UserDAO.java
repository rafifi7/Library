package com.revature.DAOs;


import com.revature.models.Book;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface{

    @Override
    public User insertUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql;
            if (user.getId() != 0) {
                sql = "INSERT INTO users (id, name, email) VALUES (?,?,?)";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getEmail());

                ps.executeUpdate();
            } else {
                sql = "INSERT INTO users (name, email) VALUES (?,?)";

                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            user.setId(rs.getInt(1));
                        }
                    }
                }

            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert User");
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getId());

            int updatedRows = ps.executeUpdate();

            if (updatedRows > 0) {
                return user;
            } else {
                System.out.println("No user with this id -> failed to update user");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update User");
        }
        return null;
    }

    @Override
    public ArrayList<User> getUsers() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users";

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            ArrayList<User> users = new ArrayList<>();

            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Book> getBooksByUserId(int user_id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books b where b.user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            ArrayList <Book> books = new ArrayList<>();

            while (rs.next()) {
                Book book = new Book (
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("user_id"),
                        rs.getBoolean("checked_out")
                );
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to find books by user id");
        }
        return null;
    }


}
