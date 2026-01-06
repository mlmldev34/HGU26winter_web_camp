import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
    // 설정을 로드하는 메서드를 클래스 내부로 이동
    public static Properties loadConfig(String configFile) {
        Properties props = new Properties();
        // 클래스 로더를 통해 리소스 파일 스트림 확보
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                System.err.println("설정 파일을 찾을 수 없음: " + configFile);
                return null;
            }
            props.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return props;
    }

    public static void main(String[] args) {
        Properties prop = loadConfig("application.properties");
        if (prop == null) return;

        String dbUrl = prop.getProperty("db.url");
        String dbUsername = prop.getProperty("db.username");
        String dbPassword = prop.getProperty("db.password");

        // try-with-resources 구문을 사용하여 자동 자원 해제 구현
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW DATABASES")) {

            System.out.println("데이터베이스 접속 성공");

            while (rs.next()) {
                // 첫 번째 컬럼(데이터베이스 명) 출력
                System.out.println("DB Name: " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("SQL 실행 오류 발생");
            e.printStackTrace();
        }
    }
}