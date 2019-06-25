package Algorithm.search;

import java.util.*;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log");
        int t = bfs.ladderLength(beginWord, endWord, wordList);
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

}
