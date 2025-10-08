package com.uts.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    public void openBooks(ActionEvent event) {
        openWindow("/view/books.fxml", "Manajemen Buku");
    }

    public void openMembers(ActionEvent event) {
        openWindow("/view/members.fxml", "Manajemen Anggota");
    }

    public void openTransactions(ActionEvent event) {
        openWindow("/view/transactions.fxml", "Peminjaman Buku");
    }

    private void openWindow(String resource, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
