package Algorithm.TrieTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords {
    class Trie {
        class TreeNode {
            char data;
            boolean isEndNode = false;
            TreeNode[] children;

            public TreeNode(char data) {
                this.data = data;
                children = new TreeNode[26];
            }
        }

        TreeNode root;
        private static final char STARTCHAR = 'a';

        public Trie() {
            root = new TreeNode('/');
        }

        public boolean insert(String word) {
            char[] wordChar = word.toCharArray();
            TreeNode now = root;
            for (int i = 0; i < wordChar.length; i++) {
                if (now.children[wordChar[i] - STARTCHAR] == null) {
                    now.children[wordChar[i] - STARTCHAR] = new TreeNode(wordChar[i]);
                }
                now = now.children[wordChar[i] - STARTCHAR];
            }
            now.isEndNode = true;
            return true;
        }

        public boolean search(String word) {
            TreeNode now = root;
            char[] wordChar = word.toCharArray();
            for (int i = 0; i < wordChar.length; i++) {
                if (now.children[wordChar[i] - STARTCHAR] == null) {
                    return false;
                }
                now = now.children[wordChar[i] - STARTCHAR];

            }
            if (now.isEndNode) {
                return true;
            }
            return false;
        }

        public boolean startWith(String word) {
            TreeNode now = root;
            char[] wordChar = word.toCharArray();
            for (int i = 0; i < wordChar.length; i++) {
                if (now.children[wordChar[i] - STARTCHAR] == null) {
                    return false;
                }
                now = now.children[wordChar[i] - STARTCHAR];
            }
            return true;
        }
    }

    Set<String> resSet = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, trie, "", i, j, visited);
            }
        }
        return new ArrayList<>(resSet);
    }

    private void dfs(char[][] board, Trie trie, String str, int x, int y, boolean[][] visited) {
        if (visited[x][y]) {
            return;
        }
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return;
        }

        str += (board[x][y]);
        if (!trie.startWith(str)) {
            return;
        }
        if (trie.search(str)) {
            resSet.add(str);
        }
        visited[x][y] = true;
        dfs(board, trie, str, x + 1, y, visited);
        dfs(board, trie, str, x - 1, y, visited);
        dfs(board, trie, str, x, y + 1, visited);
        dfs(board, trie, str, x, y - 1, visited);
        visited[x][y] = false;
    }
}
