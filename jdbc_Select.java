import java.sql.*;

public class DbTest {

    public static void main(String[] args) {

        //5개
        //1. ip(domain)
        //2. port
        //3. 계정
        //4. 패스워드
        //5. 인스턴스

        String url = "jdbc:mariadb://172.30.1.99:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "0623";

        //1. 드라이버 로드
        //2. 커넥션 객체 생성
        //3. 스테이트먼트 객체 생성
        //4. 쿼리 실행
        //5. 결과 수행
        //6. 객체 연결 해제(close)

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;

        //email, kakao, facebook
        String memberTypeValue = "email";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select member_type, user_id, password, name " + " from member " + " where member_type = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String memberType = rs.getString("member_type");
                String userId = rs.getString("user_id");
                String password = rs.getString("password");
                String name = rs.getString("name");

                System.out.println(memberType + "," + userId + "," + password + "," + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
