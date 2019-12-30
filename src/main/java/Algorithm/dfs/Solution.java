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

    private boolean[] seen;

    public boolean canReach(int[] arr, int start) {
        seen = new boolean[arr.length];
        return dfsCanReach(arr, start);
    }

    private boolean dfsCanReach(int[] arr, int start) {
        if (start < 0 || start >= arr.length) {
            return false;
        }
        if (seen[start]) {
            return false;
        }
        seen[start] = true;
        int now = start;
        if (arr[now] == 0) {
            return true;
        }
        return dfsCanReach(arr, now + arr[now]) || dfsCanReach(arr, now - arr[now]);

    }

    class State {
        int[] map = new int[26];

        public State() {
            Arrays.fill(map, -1);
        }

        private void printIntArray() {
            for (int i : map) {
                System.out.printf("%d ", i);
            }
            System.out.println("\n");
        }

        private String getKey() {
            StringBuilder sb = new StringBuilder();
            sb.append(Arrays.toString(map));
            return sb.toString();
        }
    }

    HashSet<String> isSolvableSeen = new HashSet<>();
    HashSet<Character> leads = new HashSet<>();
//    超时了
    public boolean isSolvable(String[] words, String result) {
        State state = new State();
        HashSet<Character> allChar = new HashSet<>();
        for (String s : words) {
            for (int i = 0; i < s.length(); i++) {
                if (i == 0) {
                    leads.add(s.charAt(i));
                }
                allChar.add(s.charAt(i));
            }
        }
        for (int i = 0; i < result.length(); i++) {
            if (i == 0) {
                leads.add(result.charAt(i));
            }
            allChar.add(result.charAt(i));
        }
        return dfsIsSolvable(state, words, result, allChar, new HashSet<>(), new HashSet<>());
    }

    private boolean dfsIsSolvable(State state, String[] words, String result, HashSet<Character> allChar, HashSet<Character> usedChars, HashSet<Integer> useNum) {
        String key = state.getKey();
        if (isSolvableSeen.contains(key)) {
            return false;
        }
        isSolvableSeen.add(key);
        if (usedChars.size() == allChar.size()) {
//            state.printIntArray();
            if (checkValidSol(words, result, state)) {
                return true;
            }
            return false;
        }
        for (Character character : allChar) {
            if (usedChars.contains(character)) {
                continue;
            }
            usedChars.add(character);
            for (int n = 0; n < 10; n++) {
                if (useNum.contains(n)) {
                    continue;
                }
                if (n == 0 && leads.contains(character)) {
                    continue;
                }
                state.map[character - 'A'] = n;
                useNum.add(n);
                if (dfsIsSolvable(state, words, result, allChar, usedChars, useNum)) {
                    return true;
                }
                useNum.remove(n);
                state.map[character - 'A'] = -1;
            }
            usedChars.remove(character);
        }

        return false;
    }

    private boolean checkValidSol(String[] words, String res, State state) {
        int sum = 0;
        for (String s : words) {
            int v = 0;
            for (int i = 0; i < s.length(); i++) {
                int t = state.map[s.charAt(i) - 'A'];
                if (i == 0 && t == 0) {
                    return false;
                }
                v = v * 10 + t;
            }
            sum += v;
        }
        int f = 0;
        for (int i = 0; i < res.length(); i++) {
            int t = state.map[res.charAt(i) - 'A'];
            if (i == 0 && t == 0) {
                return false;
            }
            f = f * 10 + t;
        }
        if (f == sum) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.isSolvable(new String[]{"SEND", "MORE"}, "MONEY");
//        String[] strings = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"};
//        System.out.println(solution.maxLength(new ArrayList<>(Arrays.asList(strings))));
    }
}
