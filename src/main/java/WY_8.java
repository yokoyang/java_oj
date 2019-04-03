import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WY_8 {
    private static ArrayList<Integer> vList = new ArrayList<>();

    private static int DP(int i, int now_w) {
        if (now_w <= 0) {
            return 0;
        }
        int result;
        if (i == -1) {
            return 1;
        }
        if (i == 0) {
            if (now_w > vList.get(i)) {
                return 2;
            } else {
                return 1;
            }
        }
        result = DP(i - 1, now_w) + DP(i - 1, now_w - vList.get(i));
        return result;
    }

    public static void main(String[] arsg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            vList.add(scanner.nextInt());
        }
        int res = DP(vList.size() - 1, w);
        System.out.println(res);

    }
}
