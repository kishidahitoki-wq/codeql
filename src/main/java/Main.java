import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // 外部入力 args[0] をそのまま exec に渡す（非常に危険）
            String command = args[0];
            Runtime.getRuntime().exec(command); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
