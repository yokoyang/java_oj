package Algorithm.dfs;

import java.util.Arrays;
import java.util.HashSet;

public class Solution2 {
    int[] map = new int[26];
    HashSet<String> seen = new HashSet<>();

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        solution2.show();
    }

    public void show() {
        HashSet<Character> characterHashSet = new HashSet<>();
        String s = "ABDE";
        Arrays.fill(map, -1);
        for (int i = 0; i < s.length(); i++) {
            characterHashSet.add(s.charAt(i));
        }
        dfs(characterHashSet, new HashSet<>(), new HashSet<>());
    }

    private void printIntArray(int[] ints) {
        for (int i : ints) {
            System.out.printf("%d ", i);
        }
        System.out.println("\n");
    }

    private String getKey(int[] input) {
        StringBuilder sb = new StringBuilder();
        for (int i : input) {
            sb.append(i);
            sb.append(",");
        }
        return sb.toString();
    }

    private void dfs(HashSet<Character> characterSet, HashSet<Integer> useNum, HashSet<Character> useChars) {
        String key = getKey(map);
        if (seen.contains(key)) {
            return;
        }
        seen.add(key);
        if (useChars.size() == characterSet.size()) {
            printIntArray(map);
            return;
        }
        for (Character c : characterSet) {
            if (useChars.contains(c)) {
                continue;
            }
            useChars.add(c);

            for (int i = 0; i < 10; i++) {
                if (useNum.contains(i)) {
                    continue;
                }
                map[c - 'A'] = i;
                useNum.add(i);
                dfs(characterSet, useNum, useChars);
                useNum.remove(i);
            }
            useChars.remove(c);

        }
    }
    private boolean checkValidSol(String[] words, String res, Solution.State state) {
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
}
