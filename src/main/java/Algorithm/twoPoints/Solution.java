package Algorithm.twoPoints;

import java.util.*;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int size = s.length();
        int i = 0, j = 0;
        int[] record = new int[512];
        int ans = 0;
        while (j < size) {
            while (record[s.charAt(j)] > 0) {
                record[s.charAt(i)]--;
                i++;
            }
            record[s.charAt(j)] = 1;
            ans = Math.max(ans, j - i + 1);
            j++;
        }
        return ans;
    }

    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int size = nums.length;
        if (size < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < size - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = size - 1;
            while (l < r) {
                int nowSum = nums[i] + nums[l] + nums[r];
                if (nowSum > 0) {
                    r--;
                } else if (nowSum < 0) {
                    l++;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                }
            }
        }
        return ans;
    }

    public int threeSumClosest(int[] nums, int target) {
        int size = nums.length;
        int ans = 0;
        Arrays.sort(nums);
        int dif = Integer.MAX_VALUE;
        for (int i = 0; i < size - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = size - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                int gap = sum - target;
                if (gap == 0) {
                    return sum;
                }
                if (Math.abs(gap) < dif) {
                    ans = sum;
                }
                dif = Math.min(dif, Math.abs(gap));
                if (gap > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return ans;
    }

    //输入：
    //  s = "barfoothefoobarman",
    //  words = ["foo","bar"]
    //输出：[0,9]
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        //HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            //HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.lengthOfLongestSubstring(" "));
        System.out.println(s.threeSumClosest(new int[]{0, 1, 2}, 3));
//        System.out.println(s.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
