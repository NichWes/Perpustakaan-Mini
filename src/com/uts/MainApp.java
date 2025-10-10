package com.uts;

import com.uts.controllers.MainController;
import com.uts.model.Book;
import com.uts.model.Member;
import com.uts.model.TransactionRecord;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            ObservableList<Book> books = FXCollections.observableArrayList();
            ObservableList<Member> members = FXCollections.observableArrayList();
            ObservableList<TransactionRecord> transactions = FXCollections.observableArrayList();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            MainController mainController = loader.getController();
            mainController.setSharedData(books, members, transactions);

            primaryStage.setTitle("Sistem Manajemen Perpustakaan Mini");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
