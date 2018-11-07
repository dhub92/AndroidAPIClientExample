package com.example.david.apirestconsumeexample.Models;

import java.util.Date;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String description;
    private String publisher;
    private String published_year;
    private String updated_date;
    private String _id;

    public Book(String _id,String isbn, String title, String description, String author, String publisher, String published_year, String updated_date) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.published_year = published_year;
        this.updated_date = updated_date;
        this.description = description;
        this._id=_id;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublished_year() {
        return published_year;
    }

    public void setPublished_year(String published_year) {
        this.published_year = published_year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", published_year='" + published_year + '\'' +
                ", updated_date='" + updated_date + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}
