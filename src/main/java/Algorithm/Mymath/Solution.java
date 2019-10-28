package Algorithm.Mymath;

import java.util.Arrays;

public class Solution {
    public double Power(double base, int exponent) {
        if (base == 0.0) {
            return 0.0;
        }
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double tmp = powerWithUnsignedExponent(base, Math.abs(exponent));
        if (exponent < 0) {
            return 1 / tmp;
        }
        return tmp;
    }

    private double powerWithUnsignedExponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double res = powerWithUnsignedExponent(base, exponent >> 1);
        res *= res;
        if ((exponent & 1) == 1) {
            res *= base;
        }
        return res;
    }

    //打印从1到最大为n位十进制数
    public static void printToMaxOfNDigits1(int n) {
        if(n <= 0) {
            return;
        }
        char[] nums = new char[n + 1];
        Arrays.fill(nums, '0');
        while(!increment(nums)) {
            printNum(nums);
        }
    }

    /**
     * 大数自增操作！
     * 并判断是否已经超出了n位了。
     * @param nums
     * @return
     */
    public static boolean increment(char[] nums) {
        int carry = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[i] - '0' + carry;
            //因为是加1，所以肯定是在最后一位上加1了
            if(i == nums.length - 1) {
                temp++;
            }
            carry = temp / 10;
            temp %= 10;
            nums[i] = (char)(temp + '0');
        }
        return nums[0] == '1';
    }

    public static void printNum(char[] nums) {
        int index = 0;
        for(; index < nums.length; index++) {
            if(nums[index] != '0'){
                break;
            }
        }
        for(; index < nums.length; index++) {
            System.out.print(nums[index]);
        }
        System.out.println();
    }

//    public void printToMaxOfNDigits(int n) {
//        if (n <= 0) {
//            return;
//        }
//        char[] number = new char[n];
//        for (int i = 0; i < n; i++) {
//            number[i] = '0';
//        }
//        //进位
//        int nowIndex = n - 1;
//        int increase = 0;
//        for (int i = n - 1; i >= 0; i--) {
//            number[i] += 1;
//            printNums(number);
//            if (number[i] == '9') {
//                increase += 1;
//                number[i] = '0';
//            }
//        }
//    }
//
//    private void printNums(char[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == '0') {
//                continue;
//            }
//            System.out.print(nums[i]);
//        }
//        System.out.println();
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.printToMaxOfNDigits1(3);
    }
}
