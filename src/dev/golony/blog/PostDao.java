package dev.golony.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDao {
    private Connection getConnection() throws SQLException{
        Connection conn = null;

        try{
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://localhost:3306/temp";
            conn = DriverManager.getConnection(url, "root", "jspminiproject");
        }
        catch (ClassNotFoundException e){
            System.out.println("ERROR: Driver Load Failure");
            e.printStackTrace();
        }

        return conn;
    }

    public ArrayList<PostDto> selectAll(){
        ArrayList<PostDto> result = new ArrayList<>();

        return result;
    }

    public PostDto selectOne(int idx){
        PostDto result = null;

        return result;
    }

    public boolean insert(PostDto data){
        return true;
    }

    public boolean update(PostDto data){

        return true;
    }

    public boolean delete(int idx){

        return true;
    }
}
