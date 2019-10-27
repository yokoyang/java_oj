package kickStart;

import java.util.*;

public class BuildingPalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }

    private static int solve(Scanner scanner) {
        int N = scanner.nextInt();
        int Q = scanner.nextInt();
        String have = scanner.next();
        int sum = 0;
        int[] nums;
        for (int i = 1; i <= Q; i++) {
            nums = new int[26];
            int Li = scanner.nextInt();
            int Ri = scanner.nextInt();
            String s = have.substring(Li - 1, Ri);
            char[] cList = s.toCharArray();
            for (int j = 0; j < cList.length; j++) {
                nums[cList[j] - 'A']++;
            }
            int counter = 0;
            for (int k = 0; k < 26; k++) {
                if ((nums[k] & 1) == 1) {
                    counter++;
                }
                if (counter > 1) {
                    break;
                }
            }
            if (counter <= 1) {
                sum++;
            }
        }
        return sum;
    }
}
