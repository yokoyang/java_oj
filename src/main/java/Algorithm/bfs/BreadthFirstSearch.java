package Algorithm.bfs;

import Algorithm.Solution;

import java.util.*;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        String beginWord = "hot";
        String endWord = "dog";
//        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<String> wordList = Arrays.asList("hot", "dog");
        int t= bfs.ladderLength3(beginWord, endWord, wordList);
        System.out.println(t);
    }

    private boolean similarWord(String s1, String s2) {
        int len = s1.length();
        int counter = 0;
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                counter++;
            }
        }
        return counter == 1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordDict = new HashSet<>(wordList);
        Set<String> reached = new HashSet<>();
        reached.add(beginWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.isEmpty()) {
                return 0;
            }
            reached = toAdd;
        }
        return distance;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int result = Integer.MAX_VALUE;
        int p = wordList.indexOf(endWord);
        if (p == -1) {
            return 0;
        }
        boolean has = false;
        LinkedList<String> queue = new LinkedList<>();
        HashMap<String, Integer> inqueue = new HashMap<>();
        queue.offer(beginWord);
        int loop = 1;
        while (!queue.isEmpty()) {
            String now = queue.poll();
            for (String word : wordList) {
                boolean similar = similarWord(now, word);
                if (similar) {
                    loop++;
                    int deepNow = inqueue.getOrDefault(word, 1);
                    if (deepNow == 1) {
                        queue.offer(word);
                    }
                    deepNow++;
                    if (deepNow >= loop) {
                        inqueue.put(word, loop);
                    }
                }
            }
        }
        if (!has) {
            return 0;
        }
        return result;
    }

    private HashMap<String, ArrayList<String>> record = new HashMap<>();
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return result;
        }
        Set<String> reached = new HashSet<>();
        reached.add(beginWord);
        while (!reached.isEmpty()) {
            Set<String> toAdd = new HashSet<>();
            Set<String> toDel = new HashSet<>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
//                        如果剩下的里面包含节点
                        if (wordSet.contains(word)) {
//                          当前不是最后一个
                            toAdd.add(word);
                            ArrayList<String> tmp = record.getOrDefault(word, new ArrayList<>());
                            tmp.add(each);
                            record.putIfAbsent(word, tmp);
                            toDel.add(word);
                        }
                    }
                }
            }
            wordSet.removeAll(toDel);
            reached = toAdd;
        }
        if (record.getOrDefault(endWord, new ArrayList<>()).isEmpty()) {
            return result;
        }
        LinkedList<String> l = new LinkedList<>();
        backTrace(endWord, beginWord, l);
        return result;
    }

    private void backTrace(String word, String start, LinkedList<String> list) {
        if (word.equals(start)) {
            list.add(0, start);
            result.add(new ArrayList<>(list));
            list.remove(0);
            return;
        }

        list.add(0, word);
        if (record.get(word) != null) {
            for (String s : record.get(word)) {
                backTrace(s, start, list);
            }
        }
        list.remove(0);
    }

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 0;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> reached = new HashSet<>();
        reached.add(beginWord);
        wordSet.add(endWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String tmp = new String(chars);
                        if (wordSet.contains(tmp)) {
                            toAdd.add(tmp);
                            wordSet.remove(tmp);
                        }
                    }
                }
            }
            distance += 1;
            if (toAdd.size() == 0) {
                return 0;
            }
            reached = toAdd;
        }
        return distance;
    }

    public List<List<Integer>> levelOrder(Solution.TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Solution.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                Solution.TreeNode now = queue.poll();
                currentLevel.add(now.val);
                if (now.left != null) {
                    queue.offer(now.left);
                }
                if (now.right != null) {
                    queue.offer(now.right);
                }
            }
            res.add(currentLevel);
        }
        return res;
    }
}
