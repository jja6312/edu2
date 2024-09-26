package web.dao;

import web.entity.Member;
import web.exception.MyException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
    Connection con;



    public MemberDao() throws MyException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jesdb";
            con = DriverManager.getConnection(url,"ssafy","ssafy");



        } catch (Exception e) {
            throw new MyException("연결 오류");
        }

    }

    public Member login(String id, String pw) throws MyException {
        try {
            String sql = "select * from member where id=? and pw=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                return new Member(name);
            }



        } catch (Exception e) {
            throw new MyException("로그인 오류");
        }

        return null;

    }
}
