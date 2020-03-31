package Algorithm.graph;

import java.util.*;

public class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        List<LinkedList<Integer>> g = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            g.add(new LinkedList<>());
        }
        StringBuilder res = new StringBuilder(s);
        HashSet<Integer> seen = new HashSet<>();
        List<Integer> idx = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        for (List<Integer> each : pairs) {
            int x = each.get(0);
            int y = each.get(1);
            g.get(x).add(y);
            g.get(y).add(x);
        }
        for (int i = 0; i < s.length(); i++) {
            if (seen.contains(i)) {
                continue;
            }
            idx = new ArrayList<>();
            tmp = new StringBuilder();
            DFSsmallestStringWithSwaps(i, s, seen, tmp, idx, g);
            char[] chars = tmp.toString().toCharArray();
            Collections.sort(idx);
            Arrays.sort(chars);
            for (int k = 0; k < idx.size(); k++) {
                res.setCharAt(idx.get(k), chars[k]);
            }
        }
        return res.toString();
    }

    private void DFSsmallestStringWithSwaps(int cur, String s, HashSet<Integer> seen, StringBuilder sb, List<Integer> idx, List<LinkedList<Integer>> g) {
        if (seen.contains(cur)) {
            return;
        }
        seen.add(cur);
        idx.add(cur);
        sb.append(s.charAt(cur));
        for (int v : g.get(cur)) {
            DFSsmallestStringWithSwaps(v, s, seen, sb, idx, g);
        }
    }

    int findP(int[] root, int t) {
        if (root[t] == t) {
            return t;
        }
        root[t] = findP(root, root[t]);
        return root[t];
    }

    public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        StringBuilder res = new StringBuilder(s);
        int n = s.length();
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (List<Integer> each : pairs) {
            int x = each.get(0);
            int y = each.get(1);
            root[findP(root, x)] = root[findP(root, y)];
        }
        List<List<Integer>> idx = new ArrayList<>(n);
        List<StringBuilder> ss = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            idx.add(new ArrayList<>());
            ss.add(new StringBuilder());
        }
        for (int i = 0; i < s.length(); i++) {
            int p = findP(root, i);
            idx.get(p).add(i);
            ss.get(p).append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            char[] chars = ss.get(i).toString().toCharArray();
            Arrays.sort(chars);
            if (chars.length == 0) {
                continue;
            }
            for (int j = 0; j < idx.get(i).size(); j++) {
                res.setCharAt(idx.get(i).get(j), chars[j]);
            }
        }
        return res.toString();
    }
}
