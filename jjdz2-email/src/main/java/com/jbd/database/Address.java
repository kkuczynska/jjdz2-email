package com.jbd.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Address.getAll", query = "SELECT a.addressee FROM Address a")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String addressee;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    @Override
    public String toString() {
        return "Address{" +
                "Id=" + Id +
                ", addressee='" + addressee + '\'' +
                '}';
    }
}
