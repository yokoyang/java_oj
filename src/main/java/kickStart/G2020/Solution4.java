package kickStart.G2020;


import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution4 {
    private static final char STARTCHAR = 'A';
    private static TrieNode root;

    static class TrieNode {
        private char data;
        private TrieNode[] children;
        private int visitTime = 0;
        private int depth = 0;
        public boolean isEndChar = false;

        public TrieNode(char data) {
            this.children = new TrieNode[26];
            this.data = data;
        }
    }

    /**
     * Adds a word into the data structure.
     */
    public static void addWord(String word) {
        char[] wordChar = word.toCharArray();
        TrieNode p = root;
        int dep = 0;
        for (int i = 0; i < wordChar.length; i++) {
            int index = wordChar[i] - STARTCHAR;
            if (p.children[index] == null) {
                p.children[index] = new TrieNode(wordChar[i]);
            }
            p.children[index].visitTime += 1;
            p = p.children[index];
            p.depth = ++dep;

        }
        p.isEndChar = true;
    }


    static int N = 0;
    static int K = 0;

    private static int solve(Scanner scanner) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        root = new TrieNode('/');
        scanner.nextLine();
        root.visitTime = Integer.MAX_VALUE / 2;
        for (int i = 0; i < N; i++) {
            String s = scanner.nextLine();
            addWord(s);
        }
        int groupNum = N / K;
        int sum = 0;
        sum = trace(root);
        return sum;
    }

    private static int trace(TrieNode node) {
        int maxDep = 0;
        if (node != null && node.visitTime >= K) {
            maxDep = Math.max(maxDep, node.depth);
            for (TrieNode c : node.children) {
                if (c != null && c.data == 'F') {
                    System.out.println("a");
                }
                maxDep = Math.max(maxDep, trace(c));
            }
        } else if (node != null) {
            return 0;
        }
        return maxDep;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }
}
