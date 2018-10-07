package lesson_4.Server;

import org.sqlite.SQLiteException;

import java.sql.*;

public class myBase {
    private Statement stat;
    private Connection con;
    private ResultSet rs;

    public myBase() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:baseFile.db");
        stat = con.createStatement();
        try {
            rs = stat.executeQuery("SELECT * FROM Users LIMIT 1;");
//            System.out.println(rs);
        } catch (SQLiteException e) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Users(" +
                    "Login TEXT PRIMARY KEY NOT NULL, Password TEXT NOT NULL, Nick TEXT NOT NULL, Active INT NOT NULL)");

            stat.executeUpdate("INSERT INTO Users (Login, Password, Nick, Active) VALUES ('login1', 'pass1', 'nick1', 1);");
            stat.executeUpdate("INSERT INTO Users (Login, Password, Nick, Active) VALUES ('login2', 'pass2', 'nick2', 1);");
            stat.executeUpdate("INSERT INTO Users (Login, Password, Nick, Active) VALUES ('login3', 'pass3', 'nick3', 1);");
        }
    }

    public Connection getCon() {
        return con;
    }

    public void close() throws SQLException {
        con.close();
    }

    public Statement getStat() {
        return stat;
    }
}
