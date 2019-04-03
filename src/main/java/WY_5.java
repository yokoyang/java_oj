import java.util.Scanner;

public class WY_5 {
    // 时间复杂度O(N)
// 将除数y从k+1 开始计算，除数为y时，数对的个数包括两部分： n/y*(y-k) 和多出来的另一部分，这部分需要看
// n%y 和k的大小关系

    public static void main(String[] arsg) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long sum = 0;
        for (int y = k; y <= n; y++) {
            int res = 0;
            res += (n / y) * (y - k);
            if (n % y >= k) {
                if (k > 0) {
                    res += (n % y) - k + 1;
                } else {
                    res += (n % y);
                }
            }
            sum += res;

        }

        System.out.print(sum);
    }
}
