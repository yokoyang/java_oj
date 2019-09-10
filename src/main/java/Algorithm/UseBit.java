package Algorithm;

import java.util.ArrayList;
import java.util.List;

public class UseBit {
    public int hammingWeight(int n) {
        int counter = 0;
        while (n != 0) {
            counter += 1;
            n = n & (n - 1);
        }
        return counter;
    }

    public int hammingWeight2(int n) {
        int counter = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                counter++;
            }
            n = n >> 1;
        }
        return counter;
    }

    public static void main(String[] args) {
        UseBit useBit = new UseBit();
        useBit.isPowerOfTwo(-2147483648);
        useBit.isPowerOfTwo(-2147483647);
    }

    public boolean isPowerOfTwo(int n) {

        if (n == Integer.MIN_VALUE) {
            return false;
        }
        if (n != 0 && (n & (n - 1)) == 0) {
            return true;
        }
        return false;
    }

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int counter = 0;
            int t = i;
            for (int j = 0; j < 32; j++) {
                if ((t & 1) != 0) {
                    counter++;
                }
                t = t >> 1;
            }
            res[i] = counter;
        }
        return res;
    }

    public int[] countBits2(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            if (i == 0) {
                continue;
            }
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }
}
