package test.jdbcTest;

import java.sql.*;

public class Test01 {

    public static void main(String[] args) {
        Connection con = null;

        String server = "localhost";
        String user_name = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // 드라이버 클래스 이름 변경
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?serverTimezone=UTC", user_name, password);
            System.out.println("연결 완료!");
        } catch(SQLException e) {
            System.err.println("연결 오류" + e.getMessage());
            e.printStackTrace();
        }

        //======================================================================

        String sql = "SELECT * FROM employee where id = 1";
        try {
//            Statement stmt = con.createStatement();
//            ResultSet resultSet = stmt.executeQuery(sql);
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//
//                System.out.println(id + " / " + name);
//
//            }

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println("name = " + name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //======================================================================

        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}
    }
}
