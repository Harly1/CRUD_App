package model;

public class User {
    protected int id;
    protected String name;
    protected String password;


    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String password) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
