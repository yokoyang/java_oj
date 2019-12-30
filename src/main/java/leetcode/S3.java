package leetcode;


import java.util.HashMap;
import java.util.Map;

public class S3 {
    public static void main(String[] args) {
        S3 s3 = new S3();
        System.out.println(s3.lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashMap<Character, Integer> record = new HashMap<>();
        int res = 0;
        for (int i = -1, j = 0; j < n; j++) {
            int lastPos = record.getOrDefault(s.charAt(j), -1);
            if (lastPos >= 0) {
                i = Math.max(i, lastPos);
            }
            res = Math.max(res, (j - i));
            record.put(s.charAt(j), j);
        }
        return res;
    }
}
