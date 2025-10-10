package com.uts.controllers;

import com.uts.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    private ObservableList<Book> sharedBooks;
    private ObservableList<Member> sharedMembers;
    private ObservableList<TransactionRecord> sharedTransactions;

    // Dipanggil oleh MainApp setelah load main.fxml
    public void setSharedData(ObservableList<Book> books,
                              ObservableList<Member> members,
                              ObservableList<TransactionRecord> transactions) {
        this.sharedBooks = books;
        this.sharedMembers = members;
        this.sharedTransactions = transactions;
    }

    public void openBooks(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/books.fxml"));
            Scene scene = new Scene(loader.load());

            // Ambil controller dan hubungkan data
            BooksController controller = loader.getController();
            controller.setBookList(sharedBooks);

            Stage stage = new Stage();
            stage.setTitle("Manajemen Buku");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openMembers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/members.fxml"));
            Scene scene = new Scene(loader.load());

            MembersController controller = loader.getController();
            controller.setMemberList(sharedMembers);

            Stage stage = new Stage();
            stage.setTitle("Manajemen Anggota");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openTransactions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/transactions.fxml"));
            Scene scene = new Scene(loader.load());

            TransactionsController controller = loader.getController();
            controller.setBookList(sharedBooks);
            controller.setMemberList(sharedMembers);
            controller.setTransactionList(sharedTransactions);

            Stage stage = new Stage();
            stage.setTitle("Peminjaman Buku");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
