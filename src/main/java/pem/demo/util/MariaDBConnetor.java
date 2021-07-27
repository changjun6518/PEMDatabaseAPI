package pem.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariaDBConnetor {
    public static void main(String[] args) {
        Connection con = null;

        String server = "librarymusik.synology.me";
        String port = "3306";
        String database = "PEMDB";
        String user_name = "PEMDB";
        String password = "@T806T817t818";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }
//jdbc:mariadb://librarymusik.synology.me:3306/PEMDB
        try {
            con = DriverManager.getConnection("jdbc:mysql://" +
                    server + ":" + port + "/" +
                    database +
                    "?useSSL=false", user_name, password); // SSL 실행 확인
            System.out.println("connection success! 연결 성공!!");
        } catch(SQLException e) {
            System.err.println("에러 내용 :" + e.getMessage());
            e.printStackTrace();
        }

        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}
    }
}
