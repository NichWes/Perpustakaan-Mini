package com.uts.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty phone;

    public Member(String id, String name, String address, String phone) {
        this.id = new SimpleStringProperty(id != null ? id : "");
        this.name = new SimpleStringProperty(name != null ? name : "");
        this.address = new SimpleStringProperty(address != null ? address : "");
        this.phone = new SimpleStringProperty(phone != null ? phone : "");
    }

    // Getter & Setter ID
    public String getId() { return id.get(); }
    public void setId(String id) { this.id.set(id); }
    public StringProperty idProperty() { return id; }

    // Getter & Setter Name
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    // Getter & Setter Address
    public String getAddress() { return address.get(); }
    public void setAddress(String address) { this.address.set(address); }
    public StringProperty addressProperty() { return address; }

    // Getter & Setter Phone
    public String getPhone() { return phone.get(); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public StringProperty phoneProperty() { return phone; }

    @Override
    public String toString() {
        return name.get() + " [" + id.get() + "]";
    }
}
