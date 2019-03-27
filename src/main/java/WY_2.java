import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class WY_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int count = 0;
        for (int i = n; i <= m; ++i) {
            switch (i % 3) {
                case 1:
                    break;
                case 2:
                    ++count;
                    break;
                case 0:
                    ++count;
                    break;
            }
        }
        System.out.println(count);

    }
}
