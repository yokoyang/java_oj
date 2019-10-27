package Algorithm.TrieTree;

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
            p.children[index].visitTime += 1;
            p = p.children[index];
        }
        p.isEndChar = true;
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


    public static void main(String[] args) {
        Solution obj = new Solution();
        obj.addWord("laurence");
        obj.addWord("lawrence");
        obj.addWord("laurianne");
        obj.addWord("chuanchuan"); // false
        System.out.println(obj.startsWith("lau"));
        System.out.println(obj.startsWith("chu"));
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
