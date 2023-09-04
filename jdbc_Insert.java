import java.sql.*;

public class DbTest {

    public void dbInsert() {
        String url = "jdbc:mariadb://000.00.0.00:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "####";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;

        String memberTypeValue = "email";
        String userIdValue = "1234@naver.com";
        String passwordValue = "1234";
        String nameValue = "일이삼사";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "insert into member (member_type, user_id, password, name)" + " values (? , ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);
            preparedStatement.setString(2, userIdValue);
            preparedStatement.setString(3, passwordValue);
            preparedStatement.setString(4, nameValue);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
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
