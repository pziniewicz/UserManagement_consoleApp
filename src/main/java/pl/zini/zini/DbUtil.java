package pl.zini.zini;

import java.sql.*;

public class DbUtil {

    private final static String DB_URL = System.getenv("DBURL");
    private final static String DB_NAME = System.getenv("DBNAME");
    private final static String DB_PARAM = System.getenv("DBPARAM");
    private final static String DB_USER = System.getenv("DBUSER");
    private final static String DB_PASS = System.getenv("DBPASS");

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL+DB_NAME+DB_PARAM, DB_USER, DB_PASS);
    }



    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    System.out.print(resultSet.getString(columnName) + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

