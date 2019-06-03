package com.example.jpaspecdemo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Address {
    private int id;
    private String addressName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address_name")
    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                Objects.equals(addressName, address.addressName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, addressName);
    }
}
