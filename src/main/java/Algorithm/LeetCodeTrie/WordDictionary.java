package Algorithm.LeetCodeTrie;

import java.util.List;

class WordDictionary {
    private static final char STARTCHAR = 'a';
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode('/');
    }

    class TrieNode {
        private char data;
        private TrieNode[] children;
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
        for (int i = 0; i != wordChar.length; i++) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null) {
                p.children[index] = new TrieNode(wordChar[i]);
            }
            p = p.children[index];
        }
        p.isEndChar = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        char[] wordChar = word.toCharArray();
        TrieNode p = root;
        boolean result = loopSearch(p, wordChar, 0);
        System.out.println(result);
        return result;
    }

    private boolean loopSearch(TrieNode now, char[] wchar, int p) {
        if (p >= wchar.length) {
            if (now.isEndChar) {
                return true;
            } else {
                return false;
            }
        }
        if (wchar[p] == '.') {
            boolean match = false;
            for (TrieNode t : now.children) {
                if (t != null) {
                    boolean result = loopSearch(t, wchar, p + 1);
                    if (result) {
                        match = true;
                    }
                }
            }
            return match;
        } else {
            int index = wchar[p] - STARTCHAR;
            if (now.children[index] == null) {
                return false;
            } else {
                return loopSearch(now.children[index], wchar, p + 1);
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        obj.search("pad"); // false
        obj.search("bad"); // true
        obj.search(".ad"); // true
        obj.search("b.."); // true
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
