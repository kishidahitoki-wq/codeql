import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public void doQuery(String userInput) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass");
            Statement stmt = conn.createStatement();
            
            // 文字列連結によるSQLインジェクション脆弱性
            String sql = "SELECT * FROM users WHERE id = '" + userInput + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().doQuery(args[0]); // 外部入力（引数）をそのまま渡す
    }
}
