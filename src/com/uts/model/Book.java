package com.uts.model;

import javafx.beans.property.*;

public class Book {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final ObjectProperty<Integer> year;

    public Book(String id, String title, String author, Integer year) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.year = new SimpleObjectProperty<>(year);
    }

    // Getter & Setter ID
    public String getId() { return id.get(); }
    public void setId(String id) { this.id.set(id); }
    public StringProperty idProperty() { return id; }

    // Getter & Setter Title
    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    // Getter & Setter Author
    public String getAuthor() { return author.get(); }
    public void setAuthor(String author) { this.author.set(author); }
    public StringProperty authorProperty() { return author; }

    public Integer getYear() { return year.get(); }
    public void setYear(Integer year) { this.year.set(year); }
    public ObjectProperty<Integer> yearProperty() { return year; }

    @Override
    public String toString() {
        String yearStr = (year.get() != null) ? " - " + year.get() : "";
        return title.get() + yearStr;
    }
}
