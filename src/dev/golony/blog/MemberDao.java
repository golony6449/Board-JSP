package dev.golony.blog;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemberDao {
    public Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/temp";
            conn = DriverManager.getConnection(url, "root", "jspminiproject");

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Driver Load Failure");
            e.printStackTrace();
        }

        return conn;
    }

    public MemberDto select(String data, String type){
        MemberDto result = null;

        return result;
    }

    public boolean insert(MemberDao data){

        return true;
    }

    public boolean update(MemberDao data){

        return true;
    }

    public boolean delete(String input){

        return true;
    }
}
