package Algorithm.dfs;

import java.util.*;

public class Solution {
    //    1239. Maximum Length of a Concatenated String with Unique Characters
    public int maxLength(List<String> arr) {
        int[] record = new int[26];
        DFSMaxLength(arr, record, 0, 0, new HashSet<>());
        return maxHis;
    }

    private int findMaxLen(List<String> arr, int index, int[] record) {
        int toAdd = 0;
        String nowStr = arr.get(index);
        for (int i = 0; i < nowStr.length(); i++) {
            char nowChar = nowStr.charAt(i);
            if (record[nowChar - 'a'] != 0) {
                break;
            } else {
                toAdd++;
                record[nowChar - 'a']++;
            }
        }
        if (toAdd == nowStr.length()) {
            return toAdd;
        }
        return 0;
    }

    int maxHis = 0;

    private int DFSMaxLength(List<String> arr, int[] record, int index, int score, Set<Integer> select) {
        for (int i = index; i < arr.size(); i++) {
            if (select.contains(i)) {
                continue;
            }
            select.add(i);
            int[] tmp = Arrays.copyOf(record, record.length);
            int toAdd = findMaxLen(arr, i, tmp);
            if (toAdd > 0) {
                score = DFSMaxLength(arr, tmp, i + 1, score + toAdd, select);
                maxHis = Math.max(maxHis, score);
            }
            select.remove(i);
            score -= toAdd;
        }
        return score;
    }


    //version2
    private int subStringMaxLenResult = 0;

    public int maxLength2(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        dfs(arr, "", 0);
        return subStringMaxLenResult;
    }

    private void dfs(List<String> arr, String path, int idx) {
        boolean isUniqueChar = isUniqueChars(path);
        if (isUniqueChar) {
            subStringMaxLenResult = Math.max(path.length(), subStringMaxLenResult);
        }
        if (idx == arr.size() || !isUniqueChar) {
            return;
        }

        for (int i = idx; i < arr.size(); i++) {
            dfs(arr, path + arr.get(i), i + 1);
        }
    }

    private boolean isUniqueChars(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strings = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"};
        System.out.println(solution.maxLength(new ArrayList<>(Arrays.asList(strings))));
    }
}
