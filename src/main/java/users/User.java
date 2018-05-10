package users;

public class User {
    private int id;
    private String login;
    private String password;

    User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin(){
        return login;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;

    }





}
