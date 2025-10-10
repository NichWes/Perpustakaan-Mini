package com.uts.controllers;

import com.uts.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MembersController {

    @FXML private TextField tfId;
    @FXML private TextField tfName;
    @FXML private TextField tfAddress;
    @FXML private TextField tfPhone;
    @FXML private TableView<Member> tableMembers;
    @FXML private TableColumn<Member, String> colId;
    @FXML private TableColumn<Member, String> colName;
    @FXML private TableColumn<Member, String> colAddress;
    @FXML private TableColumn<Member, String> colPhone;
    @FXML private Button btnAdd;

    private ObservableList<Member> memberList = FXCollections.observableArrayList();
    private Member editing = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(c -> c.getValue().idProperty());
        colName.setCellValueFactory(c -> c.getValue().nameProperty());
        colAddress.setCellValueFactory(c -> c.getValue().addressProperty());
        colPhone.setCellValueFactory(c -> c.getValue().phoneProperty());
        tableMembers.setItems(memberList);
    }

    @FXML
    private void onAddOrUpdate(ActionEvent event) {
        String id = tfId.getText().trim();
        String name = tfName.getText().trim();
        String address = tfAddress.getText().trim();
        String phone = tfPhone.getText().trim();

        // Validasi field wajib
        if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            showError("Mandatory field harus diisi!");
            return;
        }

        // Pengecekan bahwa telepon hanya angka
        if (!phone.matches("\\d+")) {
            showError("Nomor telepon harus angka!");
            return;
        }

        // Saat tambah
        if (editing == null) {
            for (Member m : memberList) {
                if (m.getId().equalsIgnoreCase(id)) {
                    showError("ID anggota sudah ada!");
                    return;
                }
            }
            memberList.add(new Member(id, name, address, phone));
            showMessage("Anggota berhasil ditambahkan!");
        } else { // Saat update
            editing.setName(name);
            editing.setAddress(address);
            editing.setPhone(phone);
            showMessage("Data anggota berhasil diperbarui!");
        }

        clearForm();
    }

    @FXML
    private void onEdit(ActionEvent event) {
        Member selected = tableMembers.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Pilih anggota yang ingin diedit!");
            return;
        }

        editing = selected;
        tfId.setText(selected.getId());
        tfName.setText(selected.getName());
        tfAddress.setText(selected.getAddress());
        tfPhone.setText(selected.getPhone());

        tfId.setDisable(true);
        btnAdd.setText("Update");
    }

    @FXML
    private void onDelete(ActionEvent event) {
        Member selected = tableMembers.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Pilih anggota yang ingin dihapus!");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
            "Apakah Anda yakin ingin menghapus data ini?",
            ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirmation");
        confirm.setHeaderText(null);

        confirm.showAndWait().ifPresent(res -> {
            if (res == ButtonType.YES) {
                memberList.remove(selected);
                showMessage("Data anggota berhasil dihapus!");
            }
        });
    }

    @FXML
    private void clearForm() {
        tfId.clear();
        tfName.clear();
        tfAddress.clear();
        tfPhone.clear();
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
}
