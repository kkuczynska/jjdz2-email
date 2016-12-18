package com.jbd;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Email {
    public static final int LENGTH_LIMIT = 16;
    private String from;
    private LocalDateTime data;
    private String subject;
    private String content;
    LocalDateTime objectOfDate;

    public Email(String from, String subject, Date date, String content) {
        this.content = content;
        this.from = from;
        this.data = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.subject = subject;
    }

    public Email(String from, String subject, String data, String content) {
        this.from = from;
        DateTimeFormatter formatterLongVersion = DateTimeFormatter.RFC_1123_DATE_TIME;
        DateTimeFormatter formatterShortVersion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if(data.length()> LENGTH_LIMIT){
            this.data = LocalDateTime.parse(data, formatterLongVersion);
        } else {
            this.data = LocalDateTime.parse(data,formatterShortVersion);
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(String data) {
        objectOfDate = LocalDateTime.parse(data);
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
        return "From: " + from + "\nSubject: " + subject + "\n";
    }
}
