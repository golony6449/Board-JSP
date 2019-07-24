package dev.golony.blog;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class PostDao {

    private DataSource dataSource;

    public PostDao(){
        try{
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/mariadb");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

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
        PostDto data = null;

        String query = "SELECT * FROM mvc_borad";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){
                data = new PostDto(rs.getInt("bId"), rs.getString("bName"), rs.getString("bTitle"),
                        rs.getString("bContent"), rs.getString("bDate"), rs.getInt("bHit"));

                result.add(data);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }

    public PostDto selectOne(int idx){
        PostDto result = null;



        return result;
    }

    public PostDto selectOne(String title){
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
