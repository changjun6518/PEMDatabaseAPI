package pem.demo.mobilityData;

import pem.demo.member.Member;
import pem.demo.member.MemberService;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;


public class CreateMBDataByJdbc {
    private static Connection con = null;
    private static PreparedStatement st = null;
    private final MemberService memberService;

    public CreateMBDataByJdbc(MemberService memberService) {
        this.memberService = memberService;
    }


    public void run(String filePath) throws SQLException {
        Long memberId = getByUserNameOnFile(filePath);
        connectDB();
        batchInsert2(filePath, memberId);
    }


    public void connectDB() {
        String url = "jdbc:mariadb://librarymusik.synology.me:3306/PEMDB";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url, "PEMDB", "@T806T817t818");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void batchInsert2(String filePath, Long memberId) throws SQLException {
        String sql = "INSERT INTO mobility_data(mobility_data_id,ymd,hms,unix_time,latitude,longitude,created_time,modified_time, member_id) VALUES (NULL,?,?,?,?,?,?,?,?)";
        try {
            st = con.prepareStatement(sql);
            con.setAutoCommit(false);
            insertMobilityData(filePath, memberId);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st.close();
            con.close();
        }
    }

    public void insertMobilityData(String filePath, Long memberId) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            long start = System.currentTimeMillis();
            System.out.println("Member Id : " + memberId);
            while ((line = br.readLine()) != null) {
                MobilityData mobilityData = new MobilityData(line);
                st.setString(1, mobilityData.getYmd());
                st.setString(2, mobilityData.getHms());
                st.setString(3, mobilityData.getUnixTime());
                st.setString(4, mobilityData.getLatitude());
                st.setString(5, mobilityData.getLongitude());
                st.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                st.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                st.setInt(8, Math.toIntExact(memberId));
                st.addBatch();
            }
            st.executeBatch();

            long end = System.currentTimeMillis();
            System.out.printf("execution time : %f sec\n", (float) (end - start) / 1000L);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public Long getByUserNameOnFile(String filePath) {
        String userName = "";
        Long userId = null;
        try {
            String[] dirNameSplit = filePath.split("_");
            userName = dirNameSplit[dirNameSplit.length - 1].replace(".txt", "");       //get user name (the last directory name)
            System.out.println("user : " + userName);
            Member member = memberService.findUserByUserName(userName);
            System.out.println("member : " + member.getName());

            userId = member.getId();
        } catch (Exception e) {
            System.out.println("not found user");
        }
        return userId;
    }

}
