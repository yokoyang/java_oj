package Algorithm.bitUse;

import java.util.Arrays;

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
        useBit.singleNumber_3(new int[]{1,2,1,3,2,5});
        useBit.NumberOf1(12);
        useBit.NumberOf1(-12);
        System.out.println(Integer.toBinaryString(-12));
        System.out.println(Integer.toBinaryString(12));
//        useBit.isPowerOfTwo(-2147483648);
//        useBit.isPowerOfTwo(-2147483647);
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

    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        System.out.println(count);
        return count;
    }

    //    260. Single Number III
    //Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
    //Input:  [1,2,1,3,2,5]
    //Output: [3,5]
    public int[] singleNumber_3(int[] nums) {
        int[] res = new int[2];
        int t = 0;
        for (int n : nums) {
            t ^= n;
        }
        int indexBit = 0;
        while ((t & 1) == 0) {
            indexBit++;
            t = t >> 1;
        }
        int num1 = 0, num2 = 0;
        for (int n : nums) {
            if (isBit1(n, indexBit)) {
                num1 ^= n;
            } else {
                num2 ^= n;
            }
        }
        res[0] = num1;
        res[1] = num2;
        Arrays.sort(res);
        return res;
    }

    //判断右边的index位置的数字是不是1
    private boolean isBit1(int n, int index) {
        n = n >> index;
        return (n & 1) > 0;
    }
}
