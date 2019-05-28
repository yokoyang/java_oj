package Algorithm;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i)) {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }

        }
        return maxans;
    }

    public int evalRPN(String[] tokens) {
        HashSet<String> operations = new HashSet<>();
        operations.add("*");
        operations.add("/");
        operations.add("+");
        operations.add("-");
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String now = tokens[i];
            if (operations.contains(now)) {
                //operation
                Integer f2 = stack.pollLast();
                Integer f1 = stack.pollLast();
                Integer temp;
                if (now.equals("+")) {
                    temp = f1 + f2;
                } else if (now.equals("-")) {
                    temp = f1 - f2;
                } else if (now.equals("*")) {
                    temp = f1 * f2;
                } else {
                    temp = f1 / f2;
                }
                stack.offerLast(temp);
            } else {
                stack.offerLast(Integer.valueOf(now));
            }
        }
        int result = stack.poll();
//        System.out.println(result);
        return result;
    }

    private static int solve(Scanner scanner) {
        int N = scanner.nextInt();
        int Q = scanner.nextInt();
        String have = scanner.next();
        int sum = 0;
        List<HashMap<Character, Integer>> recordList = new ArrayList<>();
        char[] allCharList = have.toCharArray();
        for (int i = 0; i < allCharList.length; i++) {
            HashMap<Character, Integer> record = new HashMap<>();
            if (i != 0) {
                record.putAll(recordList.get(recordList.size() - 1));
            }
            int t = record.getOrDefault(allCharList[i], 0);
            record.put(allCharList[i], ++t);
            recordList.add(record);
        }
        for (int i = 1; i <= Q; i++) {
            int counter = 0;
            int Li = scanner.nextInt();
            int Ri = scanner.nextInt();
            Map<Character, Integer> record = recordList.get(Ri - 1);
            for (Map.Entry entry : record.entrySet()) {
                Character letter = (Character) entry.getKey();
                int frequence = (int) entry.getValue();
                int before = 0;
                if (Li > 1) {
                    before = recordList.get(Li - 2).getOrDefault(letter, 0);

                }
                if ((frequence - before) % 2 != 0) {
                    //odd
                    counter++;
                }
                if (counter > 1) {
                    break;
                }
            }
            if (counter <= 1) {
                sum++;
            }

        }
        return sum;
    }
}
