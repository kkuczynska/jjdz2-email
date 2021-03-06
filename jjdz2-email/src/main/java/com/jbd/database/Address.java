package com.jbd.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Address.getAll", query = "SELECT a.address FROM Address a")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String address;

    public Address(String address) {
        this.address = address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Email{" +
                "Id=" + Id +
                ", address='" + address + '\'' +
                '}';
    }
}
