package com.uts.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.uts.model.Book;
import com.uts.model.Member;

public class BooksController {

    @FXML private TextField tfId;
    @FXML private TextField tfTitle;
    @FXML private TextField tfAuthor;
    @FXML private TextField tfYear;
    @FXML private TableView<Book> tableBooks;
    @FXML private TableColumn<Book, String> colId;
    @FXML private TableColumn<Book, String> colTitle;
    @FXML private TableColumn<Book, String> colAuthor;
    @FXML private TableColumn<Book, String> colYear;
    @FXML private Button btnAdd;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private Book editing = null;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        colAuthor.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        colYear.setCellValueFactory(cellData -> {
            Integer year = cellData.getValue().getYear();
            return new javafx.beans.property.SimpleStringProperty(year == null ? "-" : year.toString());
        });

        tableBooks.setItems(bookList);
    }

    @FXML
    private void onAddOrUpdate(ActionEvent event) {
        String id = tfId.getText().trim();
        String title = tfTitle.getText().trim();
        String author = tfAuthor.getText().trim();
        String yearStr = tfYear.getText().trim();

        if (id.isEmpty() || title.isEmpty()) {
            showError("ID dan Judul wajib diisi!");
            return;
        }

        Integer year = null;
        if (!yearStr.isEmpty()) {
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                showError("Tahun harus berupa angka!");
                return;
            }
        }

        // Tambah atau edit
        if (editing == null) {
            // Cek duplikat ID
            for (Book b : bookList) {
                if (b.getId().equalsIgnoreCase(id)) {
                    showError("ID buku sudah ada!");
                    return;
                }
            }

            Book book = new Book(id, title, author, year);
            bookList.add(book);
            showMessage("Buku berhasil ditambahkan!");
        } else {
            editing.setTitle(title);
            editing.setAuthor(author);
            editing.setYear(year);
            showMessage("Data buku berhasil diperbarui!");
        }

        clearForm();
    }

    @FXML
    private void onEdit(ActionEvent event) {
        Book selected = tableBooks.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Pilih buku yang ingin diedit!");
            return;
        }

        editing = selected;
        tfId.setText(selected.getId());
        tfTitle.setText(selected.getTitle());
        tfAuthor.setText(selected.getAuthor());
        tfYear.setText(selected.getYear() == null ? "" : String.valueOf(selected.getYear()));

        tfId.setDisable(true);
        btnAdd.setText("Update");
    }

    @FXML
    private void onDelete(ActionEvent event) {
    Book selected = tableBooks.getSelectionModel().getSelectedItem();
    if (selected == null) {
        showError("Pilih buku yang ingin dihapus!");
        return;
    }

    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
        "Apakah Anda yakin ingin menghapus data ini?",
        ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Konfirmasi Hapus");
        confirm.setHeaderText(null);

    confirm.showAndWait().ifPresent(res -> {
        if (res == ButtonType.YES) {
            bookList.remove(selected);
            showMessage("Data buku berhasil dihapus!");
        }
    });
}


    @FXML
    private void clearForm() {
        tfId.clear();
        tfTitle.clear();
        tfAuthor.clear();
        tfYear.clear();
        tfId.setDisable(false);
        btnAdd.setText("Tambah");
        editing = null;
    }

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

    public ObservableList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ObservableList<Book> sharedList) {
        this.bookList = sharedList;
        tableBooks.setItems(bookList);
    }
}
