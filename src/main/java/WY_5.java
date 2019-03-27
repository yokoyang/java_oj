import java.util.Scanner;

public class WY_5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String process = scanner.next();
        char[] dir = {'N', 'E', 'S', 'W'};
        int ans = 0;
        for (int i = 0; i != n; i++) {
            ans = (ans + (process.charAt(i) == 'L' ? -1 : 1) + 4) % 4;
        }
        System.out.println(dir[ans]);

    }
}
