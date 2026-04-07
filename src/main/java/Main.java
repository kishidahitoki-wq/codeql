import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // args[0] を外部からの入力ソースとして使用
        String input = args[0];

        try {
            // 外部入力をそのまま Runtime.exec() に渡す（OSコマンドインジェクション）
            // これにより、例えば 'ls; rm -rf /' のような攻撃が可能になるため、CodeQLが警告を出します
            Runtime.getRuntime().exec(input);
        } catch (IOException e) {
            // エラー処理
        }
    }
}
