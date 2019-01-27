package br.com.iagocolodetti.controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/agendadb";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() {
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection con) {
        
        try {
            if (con != null) {
                con.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement ps) {

        closeConnection(con);
        try {
            if (ps != null) {
                ps.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {

        closeConnection(con, ps);
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
