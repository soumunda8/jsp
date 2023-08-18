package test;

import dao.DBC;
import dao.MariaDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// updateMember()
public class DAOTest4 {

    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    public static void main(String[] args) {

        DBC con = new MariaDBCon();
        conn = con.connect();

        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 회원의 아이디를 입력 : ");
        String id = sc.nextLine();
        System.out.println("변경(가점/감소) 할 포인트 입력 : ");
        int point = sc.nextInt();
        int cnt = 0;
        try {
            String sql = "update member set point=point+? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, point);
            pstmt.setString(2, id);
            cnt = pstmt.executeUpdate();
            if(cnt > 0){
                System.out.println("변경 성공");
            } else {
                System.out.println("변경 실패");
            }
        } catch (SQLException e) {
            System.out.println("sql 구문에 오류가 있어 실행하지 못했습니다.");
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }

    }

}