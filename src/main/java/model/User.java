package model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "user_name", unique = false)
    protected String name;
    @Column(name = "user_password", unique = false)
    protected String password;
    @Column(name = "user_role", unique = false)
    protected String role;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

  /*  public User(int id, String name, String password) {
        this.name = name;
        this.password = password;
        this.id = id;
    }*/
    public User(int id, String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.role = role;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
