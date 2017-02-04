package com.jbd.DBA;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Form_Details implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_form_details")
    private Long id;
    private int question;
    private String response;
    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Form_Details{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", response='" + response + '\'' +
                ", form=" + form +
                '}';
    }
}
