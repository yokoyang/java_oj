package Algorithm.TrieTree;

class L208Trie {
    private TrieNode root;
    private static final char STARTCHAR = 'a';

    class TrieNode {
        private char data;
        public boolean isEndChar = false;
        public TrieNode[] children;

        public TrieNode(char data) {
            this.data = data;
            this.children = new TrieNode[26];
        }
    }

    /**
     * Initialize your data structure here.
     */
    public L208Trie() {
        root = new TrieNode('/');
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char[] wordChar = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < wordChar.length; ++i) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null) {
                p.children[index] = new TrieNode(wordChar[i]);
            }
            p = p.children[index];
        }
        p.isEndChar = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        char[] wordChar = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < wordChar.length; ++i) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null || p.children[index].data != wordChar[i]) {
                return false;
            }
            p = p.children[index];
        }
        if (p.isEndChar) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char[] wordChar = prefix.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < wordChar.length; ++i) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null || p.children[index].data != wordChar[i]) {
                return false;
            }
            p = p.children[index];
        }

        return true;

    }

    public static void main(String[] args) {
        L208Trie trie = new L208Trie();

        trie.insert("apple");
        trie.search("apple");   // returns true
        trie.search("app");     // returns false
        trie.startsWith("ab"); // returns true
        trie.insert("app");
        trie.search("app");     // returns true
    }
}

/**
 * Your L208Trie object will be instantiated and called as such:
 * L208Trie obj = new L208Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
