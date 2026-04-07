import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.File;

public class Main {
    // 1. ハードコードされた認証情報 (これは絶対に出ます)
    private static final String DB_PASSWORD = "my-secret-password-12345";

    public static void main(String[] args) {
        try {
            // 2. 外部から注入可能な環境変数を「入力ソース」にする
            String taintedInput = System.getenv("USER_ID");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "admin", DB_PASSWORD);
            Statement stmt = conn.createStatement();
            
            // 3. SQLインジェクション (確実な脆弱性)
            String sql = "SELECT * FROM users WHERE id = '" + taintedInput + "'";
            stmt.executeQuery(sql);

            // 4. パス・トラバーサル (外部入力でファイルを操作)
            File file = new File("/data/" + taintedInput);
            if (file.exists()) {
                file.delete(); // 攻撃者が任意のファイルを消せるリスク
            }

        } catch (Exception e) {
            // 5. スタックトレースの露出
            e.printStackTrace();
        }
    }
}
