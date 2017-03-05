package com.jbd;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class MailHolder implements Serializable{
    private List<Email> mails = new ArrayList<>();

    public void setMails(List<Email> mails) {
        this.mails.addAll(mails);
    }

    public List<Email> getMails() {
        return mails;
    }
}
