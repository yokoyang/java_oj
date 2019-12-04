package Algorithm.TrieTree;

import java.util.*;

public class Solution3 {
    class TrieTree {
        HashMap<String, TrieTree> children = new HashMap<>();
        String word = null;
    }

    List<String> parentFolders = new ArrayList<>();

    public List<String> removeSubfolders(String[] folders) {
        //构造字典树
        TrieTree root = new TrieTree();
        for (String str : folders) {
            String[] splitStr = str.split("/");
            TrieTree curr = root;
            for (String s : splitStr) {
                if (!s.equals("")) {
                    if (!curr.children.containsKey(s)) {
                        curr.children.put(s, new TrieTree());
                    }
                    curr = curr.children.get(s);
                }
            }
            //标识终结位置
            curr.word = str;
        }
        getFirstEndNode(root);
        return parentFolders;
    }

    private void getFirstEndNode(TrieTree root) {
        for (Map.Entry<String, TrieTree> entry : root.children.entrySet()) {
            if (entry.getValue().word != null) {
                parentFolders.add(entry.getValue().word);
            } else {
                getFirstEndNode(entry.getValue());
            }
        }
    }
}
