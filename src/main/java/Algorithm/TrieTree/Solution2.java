package Algorithm.TrieTree;


import java.util.ArrayList;
import java.util.List;

class Solution2 {
    private class TrieNode {
        int SIZE = 26;
        TrieNode children[] = new TrieNode[SIZE];
        String word = null;
        int count = 0;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        for (String product : products) {
            TrieNode curr = root;
            for (char c : product.toCharArray()) {
                if (curr.children[c - 'a'] == null)
                    curr.children[c - 'a'] = new TrieNode();
                curr = curr.children[c - 'a'];
            }
            curr.word = product;
            curr.count++;
        }

        List<List<String>> ans = new ArrayList<>();
        TrieNode curr = root;
        for (char c : searchWord.toCharArray()) {
            if (curr != null) {
                curr = curr.children[c - 'a'];
                ArrayList<String> result = new ArrayList<>();
                findAtMost3WordsByLexicographically(curr, result);
                ans.add(result);
            } else {
                ans.add(new ArrayList<>());
            }
        }
        return ans;
    }

    private void findAtMost3WordsByLexicographically(TrieNode root, ArrayList<String> result) {
        if (root == null || result.size() == 3) return;
        int size = Math.min(root.count, 3 - result.size());
        for (int i = 0; i < size; i++) {
            result.add(root.word);
        }
        for (TrieNode child : root.children)
            findAtMost3WordsByLexicographically(child, result);
    }
}