package com.uts.model;

import javafx.beans.property.*;

public class Book {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final IntegerProperty year;

    public Book(String id, String title, String author, Integer year) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.year = new SimpleIntegerProperty(year != null ? year : 0);
    }

    // Getter dan setter
    public String getId() { return id.get(); }
    public void setId(String id) { this.id.set(id); }
    public StringProperty idProperty() { return id; }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    public String getAuthor() { return author.get(); }
    public void setAuthor(String author) { this.author.set(author); }
    public StringProperty authorProperty() { return author; }

    public int getYear() { return year.get(); }
    public void setYear(Integer year) { this.year.set(year != null ? year : 0); }
    public IntegerProperty yearProperty() { return year; }

    @Override
    public String toString() {
        return title.get() + " (" + id.get() + ")";
    }
}
