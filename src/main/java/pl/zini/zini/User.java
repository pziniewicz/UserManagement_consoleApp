package pl.zini.zini;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class User {
    private long id;
    private String userName;
    private String email;
    private String password;

    public User(long id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.userName = username;
        this.password = setPassword(password);
        String query = "INSERT INTO users(email, username, password) VALUES ('"+email+"', '"+ username +"', '" +this.password +"' );";
        try {
            this.id = UserDAO.insert(DbUtil.connect(),query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteUser(long id) {
        try {
            UserDAO.delete(DbUtil.connect(),id);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        System.out.println();
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }


}
