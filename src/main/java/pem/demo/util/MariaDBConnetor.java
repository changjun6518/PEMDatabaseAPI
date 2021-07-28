package pem.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import pem.demo.domain.Member;
import pem.demo.domain.MemberRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class MariaDBConnetor {

    private static MemberRepository memberRepository;

    public MariaDBConnetor(MemberRepository memberRepository) {
        MariaDBConnetor.memberRepository = memberRepository;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

//        Member member = new Member("chang");
//        Member member = null;
//        Optional<Member> omg = memberRepository.findMemberByName("OMG");
//        if (omg.isPresent()) {
//            member = omg.get();
//        }
        Long id = 360L;
        String ymd = "123";
        String hms = "1234";
        String unixTime = "12";
        String latitude = "1";
        String longitude = "2";

        String url = "jdbc:mariadb://librarymusik.synology.me:3306/PEMDB";
        String sql = "INSERT INTO mobility_data(id,ymd,hms,unix_time,latitude,longitude) VALUES (?,?,?,?,?,?)";


        Connection con = null;
        PreparedStatement st = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url, "PEMDB", "@T806T817t818");
            st = con.prepareStatement(sql);
//            st.setObject(1, member);
            st.setLong(1, id);
            st.setString(2, ymd);
            st.setString(3, hms);
            st.setString(4, unixTime);
            st.setString(5, latitude);
            st.setString(6, longitude);


            int ret = st.executeUpdate();

            System.out.println(ret);
        } catch (SQLException e) {
            System.out.println("[SQL Error : " + e.getMessage() + "]");
        } catch (ClassNotFoundException e1) {
            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
        } finally {
            st.close();
            con.close();
        }
    }
}
