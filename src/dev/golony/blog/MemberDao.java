package dev.golony.blog;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Member;
import java.sql.*;
import java.util.ArrayList;

public class MemberDao {
    private DataSource dataSource;

    public MemberDao(){
        try {
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/MariaDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

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

    public ArrayList<MemberDto> selectAll(){
        ArrayList<MemberDto> result = new ArrayList<>();

        String query = "SELECT * FROM member";

        try {
            // 커넥션 풀 사용을 위해서는 context.xml, web.xml 모두에 관련 코드 작성이 필요함
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                MemberDto temp = new MemberDto(rs.getString("name"),
                        rs.getString("id"), rs.getString("pw"));
                result.add(temp);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

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
