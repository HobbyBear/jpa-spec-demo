package com.example.jpaspecdemo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_info", schema = "jpa_spec_demo", catalog = "")
public class UserInfo {
    private int id;
    private String username;
    private Integer addressId;

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "address_id")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id &&
                Objects.equals(username, userInfo.username) &&
                Objects.equals(addressId, userInfo.addressId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, addressId);
    }
}
