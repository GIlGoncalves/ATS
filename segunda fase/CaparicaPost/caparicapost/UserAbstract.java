package caparicapost;

import java.lang.StringBuilder;

public abstract class UserAbstract implements User {
   final String PONTO ="; ";
   final String PE = " (";
   final String PD = ")";
   
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

    public boolean equals(Object o) {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass()) return false;

        UserAbstract user = (UserAbstract) o;
        return (user.GetType().equals(this.type) && user.GetEmail().equals(this.email) && user.GetTheme().equals(this.theme) && (user.GetName().equals(this.name)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(theme != null) {
            sb.append(this.name);
            sb.append(PONTO);
            sb.append(this.email);
            sb.append(PONTO);
            sb.append(this.type );
            sb.append(PE);
            sb.append(this.theme);
            sb.append(PD);
            return sb.toString();
        } else {
            sb.append(this.name);
            sb.append(PONTO);
            sb.append(this.email);
            sb.append(PONTO);
            sb.append(this.type );
            return sb.toString();
        }
    }
}
