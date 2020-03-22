package kickStart.G2019.bMural;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main (String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int limit = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= limit; i++) {
            int[] digits = new int[in.nextInt()];
            String num = in.next();
            for (int i2 = 0; i2 < digits.length; i2++) {
                digits[i2] = Integer.parseInt(num.substring(i2, i2 + 1));
            }
            int sum = 0;
            int length = (int) Math.ceil(digits.length * 1.0 / 2);
            for (int i2 = 0; i2 < length; i2++) {
                sum += digits[i2];
            }
            int maxSum = sum;
            for (int i2 = length; i2 < digits.length; i2++) {
                sum += digits[i2] - digits[i2 - length];
                if (sum > maxSum) maxSum = sum;
            }
            System.out.printf("Case #%d: %s\n", i, maxSum);
        }
        in.close();
    }
}
