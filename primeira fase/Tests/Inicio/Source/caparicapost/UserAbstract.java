package caparicapost;


public abstract class UserAbstract implements User {

    private String type;

    private String name;

    private String email;

    private String theme;

    public UserAbstract(String type, String name, String email) {
        this.type = type;
        this.name = name;
        this.email = email;
    }

    public UserAbstract(String type, String name, String email, String theme) {
        this.type = type;
        this.name = name;
        this.email = email;
        this.theme = theme;
    }

    public String GetType() {
        return type;
    }

    public String GetName() {
        return name;
    }

    public String GetEmail() {
        return email;
    }

    public String GetTheme() {
        return theme;
    }
}
