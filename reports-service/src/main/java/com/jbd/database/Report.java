package com.jbd.database;

public class Report {
    private User user;
    private int counter;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Report(User user, int counter) {
        this.user = user;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "Report{" +
                "user=" + user +
                ", counter=" + counter +
                '}';
    }
}

