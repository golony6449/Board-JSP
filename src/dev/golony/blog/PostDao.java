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
            dataSource = (DataSource)context.lookup("java:comp/env/MariaDB");
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

        String query = "SELECT * FROM mvc_board ORDER BY bDate DESC";

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
        String query = "SELECT * FROM mvc_board WHERE bId=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                result = new PostDto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                        rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public PostDto selectOne(String title){
        PostDto result = null;



        return result;
    }

    public boolean insert(PostDto data){
        String query = "INSERT INTO mvc_board (bName, bTitle, bContent, bDate, bHit) VALUES (?, ?, ?, ?, 0)";
        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, data.getName());
            pstmt.setString(2, data.getTitle());
            pstmt.setString(3, data.getContent());
            pstmt.setString(4, data.getDate());

            int result = pstmt.executeUpdate();
            System.out.printf("쿼리 결과: %d\n", result);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean update(PostDto data){

        return true;
    }

    public boolean delete(int idx){

        return true;
    }
}
