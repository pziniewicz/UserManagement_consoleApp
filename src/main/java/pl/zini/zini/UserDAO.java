package pl.zini.zini;

import java.sql.*;
import java.util.LinkedList;

public class UserDAO {

    public static long insert(Connection conn, String query, String... params) {
        long id = 0;
        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static LinkedList<User> findAll() {
        LinkedList<User> usersList = new LinkedList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = DbUtil.connect().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String email = resultSet.getString(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);
                usersList.add(new User(id,email,username,password ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void deleteAll() {

    }


}
