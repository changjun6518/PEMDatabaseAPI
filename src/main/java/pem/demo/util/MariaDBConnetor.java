package pem.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import pem.demo.domain.Member;
import pem.demo.domain.MemberRepository;
import pem.demo.mobility.MobilityData;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MariaDBConnetor {

    private static MemberRepository memberRepository;

    public MariaDBConnetor(MemberRepository memberRepository) {
        MariaDBConnetor.memberRepository = memberRepository;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mariadb://librarymusik.synology.me:3306/PEMDB";
        String sql = "INSERT INTO mobility_data(id,ymd,hms,unix_time,latitude,longitude) VALUES (NULL,?,?,?,?,?)";


        Connection con = null;
        PreparedStatement st = null;
//        PreparedStatement stId = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url, "PEMDB", "@T806T817t818");

//            stId = con.prepareStatement("SELECT LAST_INSERT_ID()");
            st = con.prepareStatement(sql);
            con.setAutoCommit(false);

            String filePath = "C:\\Users\\ChangJun.Choi\\Desktop\\LAB\\em\\em\\rawdata\\OMG\\20200318_OMG.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
                String line;
                int i = 0;
                long start = System.currentTimeMillis();
                while ((line = br.readLine()) != null) {
                    MobilityData mobilityData = new MobilityData(line);
//                    st.setLong(1, id++);
                    st.setString(1, mobilityData.getYmd());
                    st.setString(2, mobilityData.getHms());
                    st.setString(3, mobilityData.getUnixTime());
                    st.setString(4, mobilityData.getLatitude());
                    st.setString(5, mobilityData.getLongitude());
//                    st.executeUpdate();
                    st.addBatch();
//                    if (i++ % 200 == 0) {
//                        st.executeBatch();
//                    }11587 14441
                }
                st.executeBatch();
                con.commit();
//                int ret = st.executeUpdate();
                long end = System.currentTimeMillis();
                System.out.printf("execution time : %f sec", (float)(end - start) / 1000L);
//                System.out.println(ret);
            }catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("[SQL Error : " + e.getMessage() + "]");
        } catch (ClassNotFoundException e1) {
            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
        }finally {
            st.close();
            con.close();
        }
    }
}
