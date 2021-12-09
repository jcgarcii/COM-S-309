package test.connect.myapplication.model;

public class User {

    private int id;
    private String loginname;
    private String password;
    private String displayname;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String printable(){
        return "\nUsername:  "+ getLoginname()
                +"\nDisplayname:  "+ getDisplayname()+"\n";
    }
}
