import java.util.Scanner;

public class WY_3 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int m = input.nextInt();
            String s = input.next();
            int counter = 0;
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == '.') {
                    counter++;
                    j += 2;
                }
            }
            System.out.println(counter);
        }

    }
}
