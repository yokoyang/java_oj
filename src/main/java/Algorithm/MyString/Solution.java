package Algorithm.MyString;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countAndSay(4));
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
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
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
