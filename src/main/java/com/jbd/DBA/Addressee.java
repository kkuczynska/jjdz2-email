package com.jbd.DBA;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Addressee implements Serializable{
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
        return "Addressee{" +
                "Id=" + Id +
                ", addressee='" + addressee + '\'' +
                '}';
    }
}
