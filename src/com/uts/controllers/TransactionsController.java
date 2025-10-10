package com.uts.controllers;

import com.uts.model.Book;
import com.uts.model.Member;
import com.uts.model.TransactionRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class TransactionsController {

    @FXML private ComboBox<Member> cbMember;
    @FXML private ComboBox<Book> cbBook;
    @FXML private TableView<TransactionRecord> tableTransactions;
    @FXML private TableColumn<TransactionRecord, String> colMember;
    @FXML private TableColumn<TransactionRecord, String> colBook;
    @FXML private TableColumn<TransactionRecord, String> colDate;
    @FXML private Button btnBorrow;

    // Data transaksi
    private ObservableList<TransactionRecord> transactionList = FXCollections.observableArrayList();

    // Data anggota & buku (dikirim dari MainController)
    private ObservableList<Member> memberList = FXCollections.observableArrayList();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Binding kolom tabel langsung ke property model
        colMember.setCellValueFactory(c ->
                c.getValue().getMember() != null
                        ? c.getValue().getMember().nameProperty()
                        : new javafx.beans.property.SimpleStringProperty("-"));

        colBook.setCellValueFactory(c ->
                c.getValue().getBook() != null
                        ? c.getValue().getBook().titleProperty()
                        : new javafx.beans.property.SimpleStringProperty("-"));

        colDate.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getBorrowDate() != null
                                ? c.getValue().getBorrowDate().toString()
                                : "-"));

        tableTransactions.setItems(transactionList);
    }

    /** Setter untuk data agar ComboBox otomatis terisi dari controller lain */
    public void setMemberList(ObservableList<Member> members) {
        this.memberList = members;
        cbMember.setItems(members);
    }

    public void setBookList(ObservableList<Book> books) {
        this.bookList = books;
        cbBook.setItems(books);
    }

    public void setTransactionList(ObservableList<TransactionRecord> transactions) {
        this.transactionList = transactions;
        tableTransactions.setItems(transactions);
    }

    /** Tombol Pinjam ditekan */
    @FXML
    private void onBorrow(ActionEvent event) {
        Member member = cbMember.getSelectionModel().getSelectedItem();
        Book book = cbBook.getSelectionModel().getSelectedItem();

        if (member == null || book == null) {
            showError("Silakan pilih anggota dan buku terlebih dahulu!");
            return;
        }

        // Maksimal 3 buku per anggota
        long count = transactionList.stream()
                .filter(t -> t.getMember().equals(member))
                .count();

        if (count >= 3) {
            showError("Satu anggota maksimal meminjam 3 buku!");
            return;
        }

        // Buku yang sama tidak boleh dipinjam dua kali oleh anggota yang sama
        boolean duplicate = transactionList.stream()
                .anyMatch(t -> t.getMember().equals(member) && t.getBook().equals(book));

        if (duplicate) {
            showError("Buku yang sama sudah dipinjam oleh anggota yang sama!");
            return;
        }

        // Jika lolos validasi → tambah transaksi baru
        TransactionRecord record = new TransactionRecord(member, book, LocalDate.now());
        transactionList.add(record);

        showMessage("Transaksi berhasil ditambahkan!");

        cbMember.getSelectionModel().clearSelection();
        cbBook.getSelectionModel().clearSelection();
    }

    /** Pesan Error */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
