package com.uts.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class TransactionRecord {

    private final ObjectProperty<Member> member;
    private final ObjectProperty<Book> book;
    private final ObjectProperty<LocalDate> borrowDate;

    public TransactionRecord(Member member, Book book, LocalDate borrowDate) {
        this.member = new SimpleObjectProperty<>(member);
        this.book = new SimpleObjectProperty<>(book);
        this.borrowDate = new SimpleObjectProperty<>(borrowDate != null ? borrowDate : LocalDate.now());
    }

    // Member
    public Member getMember() { return member.get(); }
    public void setMember(Member member) { this.member.set(member); }
    public ObjectProperty<Member> memberProperty() { return member; }

    // Book
    public Book getBook() { return book.get(); }
    public void setBook(Book book) { this.book.set(book); }
    public ObjectProperty<Book> bookProperty() { return book; }

    // Borrow Date
    public LocalDate getBorrowDate() { return borrowDate.get(); }
    public void setBorrowDate(LocalDate date) { this.borrowDate.set(date); }
    public ObjectProperty<LocalDate> borrowDateProperty() { return borrowDate; }

    @Override
    public String toString() {
        String memberName = (getMember() != null) ? getMember().getName() : "Unknown Member";
        String bookTitle = (getBook() != null) ? getBook().getTitle() : "Unknown Book";
        String dateStr = (getBorrowDate() != null) ? getBorrowDate().toString() : "No Date";

        return memberName + " → " + bookTitle + " (" + dateStr + ")";
    }
}
