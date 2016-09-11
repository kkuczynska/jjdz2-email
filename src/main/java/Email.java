import java.util.Date;

/**
 * Created by marcinb on 11.09.16.
 */
public class Email {
    private String From;
    private String To;
    private Date data;
    private String Subject;


    public Email(String from, String to, Date data, String subject) {
        From = from;
        To = to;
        this.data = data;
        Subject = subject;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
