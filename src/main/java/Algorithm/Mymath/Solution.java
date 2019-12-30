package Algorithm.Mymath;

import java.util.*;

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

    //    1291. Sequential Digits
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            int s = i;
            int next = i + 1;
            while (s <= high && next < 10) {
                s = s * 10 + next;
                if (s >= low && s <= high) {
                    res.add(s);
                }
                next++;
            }
        }
        Collections.sort(res);
        return res;
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
        if (n <= 0) {
            return;
        }
        char[] nums = new char[n + 1];
        Arrays.fill(nums, '0');
        while (!increment(nums)) {
            printNum(nums);
        }
    }

    /**
     * 大数自增操作！
     * 并判断是否已经超出了n位了。
     *
     * @param nums
     * @return
     */
    public static boolean increment(char[] nums) {
        int carry = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[i] - '0' + carry;
            //因为是加1，所以肯定是在最后一位上加1了
            if (i == nums.length - 1) {
                temp++;
            }
            carry = temp / 10;
            temp %= 10;
            nums[i] = (char) (temp + '0');
        }
        return nums[0] == '1';
    }

    public static void printNum(char[] nums) {
        int index = 0;
        for (; index < nums.length; index++) {
            if (nums[index] != '0') {
                break;
            }
        }
        for (; index < nums.length; index++) {
            System.out.print(nums[index]);
        }
        System.out.println();
    }

    public static void printToMaxOfNDigits2(int n) {
        if (n <= 0) {
            return;
        }
        char[] nums = new char[n];
        recursiveProductNum(0, n, nums);
    }

    public static void recursiveProductNum(int index, int length, char[] nums) {
        if (index == length) {
            printNum(nums);
            return;
        }
        for (char i = '0'; i <= '9'; i++) {
            nums[index] = i;
            recursiveProductNum(index + 1, length, nums);
        }
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

    //    整数中1出现的次数（从1到n整数中1出现的次数）
    //方法1：把它变成char来统计
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        while (n > 0) {
            String str = String.valueOf(n);
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    count++;
                }
            }
            n--;
        }
        return count;
    }

    //或者mod10 再除10
    public int NumberOf1Between1AndN_Solution2(int n) {
        int count = 0;
        int tmp;
        while (n > 0) {
            tmp = n--;
            while (tmp != 0) {
                int mod = tmp % 10;
                if (mod == 1) {
                    count++;
                }
                tmp /= 10;
            }
        }
        return count;
    }
    //数学法，通过对每一位进行分析，递归


    //    输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//    例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
// * 解题思路：
//            * 先将整型数组转换成String数组，然后将String数组排序，最后将排好序的字符串数组拼接出来。关键就是制定排序规则。
//            * 排序规则如下：
//            * 若ab > ba 则 a > b，
//            * 若ab < ba 则 a < b，
//            * 若ab = ba 则 a = b；
//            * 解释说明：
//            * 比如 "3" < "31"但是 "331" > "313"，所以要将二者拼接起来进行比较
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        int len = numbers.length;
        String[] str = new String[len];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, (s1, s2) -> {
            String c1 = s1 + s2;
            String c2 = s2 + s1;
            return c1.compareTo(c2);
        });
        for (int i = 0; i < len; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }

    //    把只包含质因子2、3和5的数称作丑数（Ugly Number）。
    //    例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    /*
    说下思路，如果p是丑数，那么p=2^x * 3^y * 5^z
    那么只要赋予x,y,z不同的值就能得到不同的丑数。
    如果要顺序找出丑数，要知道下面几个特（fei）点（hua）。
    对于任何丑数p：
    （一）那么2*p,3*p,5*p都是丑数，并且2*p<3*p<5*p
    （二）如果p<q, 那么2*p<2*q,3*p<3*q,5*p<5*q
    现在说说算法思想：
        由于1是最小的丑数，那么从1开始，把2*1，3*1，5*1，进行比较，得出最小的就是1
    的下一个丑数，也就是2*1，
        这个时候，多了一个丑数‘2’，也就又多了3个可以比较的丑数，2*2，3*2，5*2，
    这个时候就把之前‘1’生成的丑数和‘2’生成的丑数加进来也就是
    (3*1,5*1,2*2，3*2，5*2)进行比较，找出最小的。。。。如此循环下去就会发现，
    每次选进来一个丑数，该丑数又会生成3个新的丑数进行比较。
        上面的暴力方法也应该能解决，但是如果在面试官用这种方法，估计面试官只会摇头吧
    。下面说一个O（n）的算法。
        在上面的特（fei）点（hua）中，既然有p<q, 那么2*p<2*q，那么
    “我”在前面比你小的数都没被选上，你后面生成新的丑数一定比“我”大吧，那么你乘2
    生成的丑数一定比我乘2的大吧，那么在我选上之后你才有机会选上。
    其实每次我们只用比较3个数：用于乘2的最小的数、用于乘3的最小的数，用于乘5的最小的
    数。也就是比较(2*x , 3*y, 5*z) ，x>=y>=z的，
*/
    //获得第n个丑数
    public int GetUglyNumber_Solution(int n) {
        if (n <= 0) return 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (list.size() < n)//循环的条件
        {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);
            if (min == m2) i2++;
            if (min == m3) i3++;
            if (min == m5) i5++;
        }
        return list.get(list.size() - 1);
    }

    //数字序列中某一位的数字
    //数字序列 0123456789101112，求第n位的数字
    //https://blog.csdn.net/m0_37862405/article/details/80341260


    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.GetUglyNumber_Solution(5);
//        solution.printToMaxOfNDigits2(3);
        System.out.println(solution.sequentialDigits(100, 300));
    }
}
