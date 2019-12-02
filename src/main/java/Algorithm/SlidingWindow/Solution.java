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

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring2("tmmzuxt"));
//        System.out.println(solution.numberOfSubarrays(new int[]{1, 1, 2, 1, 2, 0, 1, 1, 1, 2}, 3));

    }
}
