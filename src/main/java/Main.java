import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            String input = args[0]; // 外部からの入力
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass");
            Statement stmt = conn.createStatement();

            // ⚠️ 確実に検知されるSQLインジェクション
            // 外部入力をそのままクエリに結合しているため、CodeQLが「致命的(Critical)」と判定します
            String sql = "SELECT * FROM users WHERE name = '" + input + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            // これも「情報の露出」として検知されやすい
            e.printStackTrace();
        }
    }
}
