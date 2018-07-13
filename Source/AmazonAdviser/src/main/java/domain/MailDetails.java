package domain;

public class MailDetails {

    private String username, password, from, to;

    public MailDetails() {
    }

    public MailDetails(String username, String password, String from, String to) {
        this.username = username;
        this.password = password;
        this.from = from;
        this.to = to;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
