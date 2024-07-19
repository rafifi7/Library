package com.revature.models;

public class Book {

    private int id;
    private String title;
    private String author;
    private int user_id;
    private boolean checked_out;

    public Book () {

    }
    public Book(int id, String title, String author, int user_id, boolean checked_out) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.user_id = user_id;
        this.checked_out = checked_out;
    }

    public Book(String title, String author, int user_id, boolean checked_out) {
        this.title = title;
        this.author = author;
        this.user_id = user_id;
        this.checked_out = checked_out;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isChecked_out() {
        return checked_out;
    }

    public void setChecked_out(boolean checked_out) {
        this.checked_out = checked_out;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", user_id=" + user_id +
                ", checked_out=" + checked_out +
                '}';
    }
}

