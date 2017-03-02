package com.jbd;


import com.jbd.authorization.SessionData;

public class Report {
    private SessionData user;
    private int counter;

    public SessionData getUser() {
        return user;
    }

    public void setUser(SessionData user) {
        this.user = user;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Report(SessionData user, int counter) {
        this.user = user;
        this.counter = counter;
    }

    public Report() {
    }

    @Override
    public String toString() {
        return "Report{" +
                "user=" + user +
                ", counter=" + counter +
                '}';
    }
}
