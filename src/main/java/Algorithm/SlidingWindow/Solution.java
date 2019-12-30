package Algorithm.SlidingWindow;

import java.util.*;

public class Solution {
    //1248. Count Number of Nice Subarrays
    public int numberOfSubarrays2(int[] nums, int k) {
        int i = 0, j = 0, count = 0, left = 0, right = 0, n = nums.length, oddCount = 0;
        while (i < n && j < n) {
            //1. move j, oddCount == k right++;
            right = 0;
            while (j < n && (oddCount < k || oddCount == k && nums[j] % 2 == 0)) {
                if (nums[j] % 2 == 1) oddCount++;
                if (oddCount == k) right++;
                j++;
            }
            // if j == n, oddCount < k out;
            if (j == n && oddCount < k) break;
            //2. move i, oddCount == k left++;
            left = 0;
            while (i < j && oddCount == k) {
                left++;
                if (nums[i++] % 2 == 1) oddCount--;
            }
            //3. + combination of left and right boundary;
            count += left * right;
        }
        return count;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int i = 0, j = 0, count = 0, left = 0, right = 0, n = nums.length, oddCount = 0;
        while (i < n && j < n) {
            right = 0;
            while (j < n && (oddCount < k || nums[j] % 2 == 0)) {
                if (nums[j] % 2 == 1) oddCount++;
                if (oddCount == k) {
                    right++;
                }
                j++;
            }
            if (oddCount < k && j == n) {
                break;
            }
            left = 0;
            while (i < j && oddCount >= k) {
                left++;
                if (nums[i] % 2 == 1) {
                    oddCount--;
                }
                i++;
            }
            count += (left * right);
        }
        return count;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                System.out.println(s.charAt(j));
                i = Math.max(i, map.get(s.charAt(j)));
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    //1234. Replace the Substring for Balanced String
    //    有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
    //假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」
    //    给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
    // 使用滑动窗口
    public int balancedString(String s) {
        int[] count = new int[128];
        int n = s.length(), res = n, i = 0, k = n / 4;
        for (int j = 0; j < n; ++j) {
            ++count[s.charAt(j)];
        }
        for (int j = 0; j < n; ++j) {
            --count[s.charAt(j)];
            while (i < n && count['Q'] <= k && count['W'] <= k && count['E'] <= k && count['R'] <= k) {
                res = Math.min(res, j - i + 1);
                ++count[s.charAt(i++)];
            }
        }
        return res;
    }

    // 使用滑动窗口 [i, j) 左闭右开
    public int balancedString2(String s) {
        int res = s.length();
        int size = s.length();
        int i = 0, j = 0, k = size / 4;
        int[] record = new int[128];
        for (; j < size; j++) {
            record[s.charAt(j)]++;
        }
        j = 0;
        while (j >= i) {
            if (record['Q'] <= k && record['W'] <= k && record['E'] <= k && record['R'] <= k) {
                res = Math.min(res, j - i);
                record[s.charAt(i)]++;
                i++;
            } else {
                if (j >= size) {
                    break;
                }
                record[s.charAt(j)]--;
                j++;
            }
        }

        return res;
    }

    //    1297. Maximum Number of Occurrences of a Substring
    public int maxFreq2(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> record = new HashMap<>();
        int n = s.length(), resCnt = 0;
        if (minSize > n) return 0;
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> letterMap = new HashMap<>();
            for (int distNum = 0, j = 0; j < maxSize; j++) {
                if (i + j >= n) break;
                letterMap.put(s.charAt(i + j), letterMap.getOrDefault(s.charAt(i + j), 0) + 1);
                if (letterMap.get(s.charAt(i + j)) == 1) distNum++;
                if (distNum > maxLetters) break;
                if (j >= minSize - 1) {
                    String substring = s.substring(i, i + j + 1);
                    record.put(substring, record.getOrDefault(substring, 0) + 1);
                }
            }
        }
        for (String str : record.keySet()) {
            if (record.get(str) > resCnt)
                resCnt = record.get(str);
        }
        return resCnt;
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int res = 0;
        if (minSize > s.length()) {
            return res;
        }
        int[] letterDic;
        int i = 0, j = 0;
        HashMap<String, Integer> record = new HashMap<>();
        for (i = 0; i < s.length(); i++) {
            letterDic = new int[26];
            int distinct = 0;
            for (j = 0; j < maxSize && (i + j < s.length()); j++) {
                char now = s.charAt(i + j);
                if (letterDic[now - 'a']++ == 0) {
                    distinct++;
                }
                if (distinct > maxLetters) {
                    break;
                }
                if (j >= minSize - 1) {
                    String sub = s.substring(i, i + j + 1);
                    int last = record.getOrDefault(sub, 0);
                    record.put(sub, last + 1);
                }
            }
        }
        for (Integer v : record.values()) {
            res = Math.max(res, v);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.balancedString("QWERQQER"));
        System.out.println(solution.maxFreq("aababcaab"
                , 2
                , 3
                , 4));
//        System.out.println(solution.lengthOfLongestSubstring2("tmmzuxt"));
//        System.out.println(solution.numberOfSubarrays(new int[]{1, 1, 2, 1, 2, 0, 1, 1, 1, 2}, 3));

    }
}
