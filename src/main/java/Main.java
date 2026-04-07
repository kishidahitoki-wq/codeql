import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    // 1. 「ハードコードされたパスワード」：これだけで警告が出ます
    private static final String ADMIN_PASSWORD = "password12345";

    public static void main(String[] args) {
        try {
            // 2. 「安全でないDB接続」：パスワードがソースコード内にあるため検知されます
            DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db", 
                "admin", 
                ADMIN_PASSWORD
            );
        } catch (Exception e) {
            // 3. 「スタックトレースの露出」：例外をそのまま標準出力するのはセキュリティリスクと見なされます
            e.printStackTrace();
        }
    }
}
