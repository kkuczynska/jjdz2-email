package com.jbd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Email {
    private String from;
    private Date data;
    private String subject;
    private String content;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date objectOfDate;

    public Email(String from, String subject, String data, String content) {
        this.from = from;
        try {
            objectOfDate = simpleDateFormat.parse(data);
            this.data = objectOfDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.subject = subject;
        this.content = content;
    }

    public Email(String from, String subject) {
        this.from = from;
        this.subject = subject;

    }
    public Email(String from) {
        this.from = from;
    }

    public Date getData() {
        return data;
    }

    public void setData(String data) {
        try {
            objectOfDate = simpleDateFormat.parse(data);
            this.data = objectOfDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return from + " " + subject + " " + data.toString();
    }
}
