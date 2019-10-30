package Algorithm.MyString;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.reMatch("".toCharArray(), ".*".toCharArray()));
//        System.out.println(s.countAndSay(4));
//        System.out.println(s.longestCommonPrefix(new String[]{"aa", "a"}));
//        System.out.println(s.isPalindrome("ASd"));
//        System.out.println(s.firstUniqChar("loveleetcode"));
//        System.out.println(s.reverse(1534236469));
    }

    public void reverseString(char[] s) {
        int size = s.length;
        int half = size / 2;
        char tmp;
        for (int i = 0; i < half; i++) {
            tmp = s[i];
            s[i] = s[size - 1 - i];
            s[size - 1 - i] = tmp;
        }
    }

    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x *= -1;
        }
        List<Integer> res = new ArrayList<>();
        while (x > 0) {
            int mod = x % 10;
            res.add(mod);
            x /= 10;
        }
        int num = 0;
        int max = Integer.MAX_VALUE / 10;
        for (Integer re : res) {
            if (num > max) {
                return 0;
            }
            num = num * 10 + re;
        }
        if (negative) {
            num *= -1;
        }
        return num;
    }

    public int firstUniqChar(String s) {
        Map<Character, Integer> feq = new HashMap<>();
        Map<Character, Integer> index = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            feq.put(s.charAt(i), feq.getOrDefault(s.charAt(i), 0) + 1);
            index.put(s.charAt(i), i);
        }

        int min = Integer.MAX_VALUE;
        for (char c : feq.keySet()) {
            if (feq.get(c) == 1) {
                min = Math.min(min, index.get(c));
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int firstUniqChar2(String s) {
        HashMap<Character, Integer> record = new HashMap<>();
        HashSet<Integer> usedSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);
            if (record.containsKey(now)) {
                usedSet.add(i);
                usedSet.add(record.get(now));
            }
            record.put(now, i);
        }
        for (int i = 0; i < s.length(); i++) {
            if (!usedSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> record = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            record.put(s.charAt(i), record.getOrDefault(s.charAt(i), 0) + 1);
            record.put(t.charAt(i), record.getOrDefault(t.charAt(i), 0) - 1);
        }
//        for (Character character : record.keySet()) {
//            if (record.get(character) > 0) {
//                return false;
//            }
//        }
        for (Map.Entry entry : record.entrySet()) {
            if ((Integer) entry.getValue() > 0) {
                return false;
            }

        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private char getTranslatedChar(char ch) {
        return Character.isDigit(ch) ? ch : Character.toLowerCase(ch);
    }

    public boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start))) start++;
            while (start < end && !Character.isLetterOrDigit(s.charAt(end))) end--;
            if (start < end) {
                char ch1 = getTranslatedChar(s.charAt(start)), ch2 = getTranslatedChar(s.charAt(end));
                if (ch1 != ch2) return false;
                start++;
                end--;
            }
        }
        return (start >= end);
    }

    public int myAtoi(String str) {
        if (str == null || str.length() < 1)
            return 0;

        // trim white spaces at beginning and end
        str = str.trim();

        char flag = '+';

        // check negative or positive
        int i = 0;
        if (str.charAt(0) == '-') {
            flag = '-';
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        int result = 0;

        // calculate value
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (Integer.MAX_VALUE / 10 < result || (Integer.MAX_VALUE / 10 == result && Integer.MAX_VALUE % 10 < (str.charAt(i) - '0')))
                return flag == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if (flag == '-')
            result = -result;

        return result;
    }

    public int myAtoi2(String str) {
        if (str.length() == 0) {
            return 0;
        }
        int i = 0, sign = 1;
        double result = 0;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        if (i == str.length()) {
            return 0;
        }
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            sign = (str.charAt(i) == '+') ? 1 : -1;
            i++;
        }
        while (i < str.length()) {
            int num = str.charAt(i) - '0';
            if (num < 0 || num > 9) {
                break;
            }
            result = result * 10 + num;
            i++;
        }
        result *= sign;
        return (int) result;
    }

    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (' ' == str.charAt(i)) {
                count++;
            }
        }
        int oldIndex = str.length() - 1;
        int newLength = str.length() + count * 2;
        int newIndex = newLength - 1;
        str.setLength(newLength);
        for (; oldIndex >= 0 && newIndex > oldIndex; oldIndex--) {
            if (str.charAt(oldIndex) == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                str.setCharAt(newIndex--, str.charAt(oldIndex));
            }
        }
        return str.toString();
    }

    //正则表达式匹配
    //请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
    // 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
    public boolean reMatch(char[] str, char[] pattern) {
        if (str.length == 0 && pattern.length == 0) {
            return true;
        }
        return reMatchCore(str, 0, str.length, pattern, 0, pattern.length);
    }

    //i和j表示当前使用了多少个元素
    private boolean reMatchCore(char[] str, int i, int len1, char[] pattern, int j, int len2) {
        boolean firstMatch = false;
        if (j == len2) {
            if (i == len1) {
                return true;
            } else {
                return false;
            }
        }
        if (i < len1 && (str[i] == pattern[j] || pattern[j] == '.')) {
            firstMatch = true;
        }

        if (j < len2 - 1 && pattern[j + 1] == '*') {
            return reMatchCore(str, i, len1, pattern, j + 2, len2) || (firstMatch && reMatchCore(str, i + 1, len1, pattern, j, len2));
        } else {
            return firstMatch && reMatchCore(str, i + 1, len1, pattern, j + 1, len2);
        }
    }

    //使用动态规划，完成正则匹配
    //i和j表示当前使用了多少个元素
    public boolean reMatch2(char[] str, char[] pattern) {
        if (str.length == 0 && pattern.length == 0) {
            return true;
        }
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[0][0] = true;
        for (int j = 1; j <= pattern.length; j++) {
            if (pattern[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            } else {
                dp[0][j] = false;
            }
        }
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if (pattern[j] == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || (first_match(str, pattern, i, j - 1) && dp[i][j + 1]);
                } else {
                    dp[i + 1][j + 1] = first_match(str, pattern, i, j) && dp[i][j];
                }
            }
        }
        return dp[str.length][pattern.length];
    }

    private boolean first_match(char[] s, char[] p, int i, int j) {
        return s[i] == p[j] || p[j] == '.';
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int nowMinLen;
        String common = strs[0];
        for (int i = 1; i < strs.length; i++) {
            nowMinLen = Math.min(common.length(), strs[i].length());
            int j;
            for (j = 0; j < nowMinLen; j++) {
                if (common.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            common = common.substring(0, j);

        }
        return common;
    }

    public String countAndSay(int n) {
        if (n == 1) return "1";
        int count;
        StringBuilder sb = new StringBuilder("1");
        int len;
        while (n-- > 1) {
            char[] arr = sb.toString().toCharArray();
            len = arr.length;
            sb.setLength(0);
            for (int i = 0; i < len; i++) {
                count = 1;
                while (i < len - 1 && arr[i + 1] == arr[i]) {
                    count++;
                    i++;
                }
                sb.append(count).append(arr[i]);
            }
        }
        return sb.toString();
    }
}
