package Algorithm.TrieTree;

import java.util.*;

class Solution {
    private static final char STARTCHAR = 'a';
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Solution() {
        root = new TrieNode('/');
    }

    class TrieNode {
        private char data;
        private TrieNode[] children;
        private int visitTime = 0;
        private int endTime = 0;
        public boolean isEndChar = false;

        public TrieNode(char data) {
            this.children = new TrieNode[26];
            this.data = data;
        }
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        char[] wordChar = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < wordChar.length; i++) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null) {
                p.children[index] = new TrieNode(wordChar[i]);
            }
            p.children[index].visitTime += 1;
            p = p.children[index];
        }
        p.isEndChar = true;
        p.endTime += 1;

    }

    public int startsWith(String prefix) {
        char[] wordChar = prefix.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < wordChar.length; ++i) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null || p.children[index].data != wordChar[i]) {
                return 0;
            }
            p = p.children[index];
        }
        return p.visitTime;
    }

    public List<String> startWithNames(String prefix, int limit) {
        ArrayList<String> res = new ArrayList<>();
        char[] wordChar = prefix.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < wordChar.length; ++i) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null || p.children[index].data != wordChar[i]) {
                return res;
            }
            p = p.children[index];
        }
        findLimit(p, limit, new StringBuilder(prefix), res);
        return res.subList(0, Math.min(res.size(), limit));
    }

    private void findLimit(TrieNode p, int limit, StringBuilder sb, ArrayList<String> tmp) {
        if (tmp.size() >= limit) {
            return;
        }
        if (p.isEndChar) {
            int t = p.endTime;
            while (t > 0) {
                tmp.add(sb.toString());
                t--;
            }
        }
        for (TrieNode child : p.children) {
            if (child == null) {
                continue;
            }
            sb.append(child.data);
            findLimit(child, limit, sb, tmp);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for (String p : products) {
            addWord(p);
        }
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            res.add(startWithNames(searchWord.substring(0, i + 1), 3));
        }
        return res;
    }

    //使用查找的方法，替代字典树，实现搜索匹配
    //    1268. Search Suggestions System
    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);
        for (int i = 0; i < searchWord.length(); i++) {
            List<String> list = new ArrayList<>();
            String match = searchWord.substring(0, i + 1);
            for (String s : products) {
                if (s.startsWith(match) && list.size() < 3) list.add(s);
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        obj.addWord("laurence");
        obj.addWord("lawrence");
        obj.addWord("laurianne");
        obj.addWord("lauxxrianne");
        obj.addWord("chuanchuan"); // false
//        System.out.println(obj.startsWith("lau"));
        System.out.println(obj.suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"));
//        System.out.println(obj.startsWith("chu"));
    }
}


