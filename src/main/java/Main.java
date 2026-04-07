import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            // threat-models: [local] に設定することで、
            // args[0] が「危険な汚染源（Tainted Source）」として認識されるようになります。
            String userInput = args[0];

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass");
            Statement stmt = conn.createStatement();

            // SQLインジェクション (Critical)
            String sql = "SELECT * FROM users WHERE id = '" + userInput + "'";
            stmt.executeQuery(sql);

            // OSコマンドインジェクション (Critical)
            Runtime.getRuntime().exec(userInput);

        } catch (Exception e) {
            // 情報の露出 (Medium)
            e.printStackTrace();
        }
    }
}
