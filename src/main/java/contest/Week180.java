package contest;

import java.text.SimpleDateFormat;
import java.util.*;

public class Week180 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> m = new HashMap<>(8, 1.0f);
        m.put(1, 2);
        m.put(2, 3);
        m.put(3, 4);
        m.put(4, 5);
        m.put(5, 6);
        m.put(6, 7);
        m.put(7, 8);
        m.put(8, 9);
        m.put(9, 10);


        Week180 week180 = new Week180();
        week180.frogPosition(7,
                new int[][]{{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}},
                2,
                4);
//        PriorityQueue<Integer> p = new PriorityQueue<>(3, (a, b) -> (a - b));
//        System.out.println(p.size());
//        p.offer(1);
//        p.offer(2);
//        p.offer(4);
//        p.offer(5);
//        System.out.println(p.poll());
//        CustomStack customStack = new CustomStack(3); // Stack is Empty []
//        customStack.push(1);                          // stack becomes [1]
//        customStack.push(2);                          // stack becomes [1, 2]
//        System.out.println(customStack.pop());                            // return 2 --> Return top of the stack 2, stack becomes [1]
//        customStack.push(2);                          // stack becomes [1, 2]
//        customStack.push(3);                          // stack becomes [1, 2, 3]
//        customStack.push(4);                          // stack still [1, 2, 3], Don't add another elements as size is 4
//        customStack.increment(5, 100);                // stack becomes [101, 102, 103]
//        customStack.increment(2, 100);                // stack becomes [201, 202, 103]
//        System.out.println(customStack.pop());                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
//        System.out.println(customStack.pop());                            // return 202 --> Return top of the stack 102, stack becomes [201]
//        System.out.println(customStack.pop());                            // return 201 --> Return top of the stack 101, stack becomes []
//        System.out.println(customStack.pop());                            // return -1 --> Stack is empty return -1.
    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rowM = new int[m];
        int[] colM = new int[n];
        for (int i = 0; i < m; i++) {
            int t = matrix[i][0];
            for (int j = 0; j < n; j++) {
                t = Math.min(t, matrix[i][j]);
            }
            rowM[i] = t;
        }
        for (int i = 0; i < n; i++) {
            int t = matrix[0][i];
            for (int j = 0; j < m; j++) {
                t = Math.max(t, matrix[j][i]);
            }
            colM[i] = t;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == rowM[i] && matrix[i][j] == colM[j]) {
                    res.add(matrix[i][j]);
                }
            }
        }
        return res;
    }

    static class CustomStack {
        LinkedList<Integer> stack;
        int maxS;
        int[] inc;

        public CustomStack(int maxSize) {
            maxS = maxSize;
            stack = new LinkedList<>();
            inc = new int[maxS];
        }

        public void push(int x) {
            if (stack.size() >= maxS) {
                return;
            }
            stack.addLast(x);
        }

        public int pop() {
            int i = stack.size() - 1;
            if (i < 0) {
                return -1;
            }
            if (i > 0) {
                inc[i - 1] += inc[i];
            }
            int res = stack.pollLast() + inc[i];
            inc[i] = 0;
            return res;
        }

        public void increment(int k, int val) {
            int c = Math.min(k, stack.size());
            if (c > 0) {
                inc[c - 1] += val;
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<Integer> numsArr;

    public TreeNode balanceBST(TreeNode root) {
        numsArr = new ArrayList<>();
        if (root == null) {
            return root;
        }
        inOrder(root);
        return buildFromList(0, numsArr.size() - 1);
    }

    TreeNode buildFromList(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode t = new TreeNode(numsArr.get(mid));
        t.left = buildFromList(start, mid - 1);
        t.right = buildFromList(mid + 1, end);
        return t;
    }

    void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        numsArr.add(node.val);
        inOrder(node.right);
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int modK = (int) 1e9 + 7;
        int[][] ess = new int[n][2];
        for (int i = 0; i < n; ++i) {
            ess[i] = new int[]{efficiency[i], speed[i]};
        }
        Arrays.sort(ess, (a, b) -> b[0] - a[0]);
        //小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);
        long res = 0, sumS = 0;
        for (int[] es : ess) {
            pq.offer(es[1]);
            sumS += es[1];
            if (pq.size() > k) {
                sumS -= pq.poll();
            }
            res = Math.max(res, sumS * es[0]);
        }
        return (int) (res % modK);
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {
        double[] p = new double[n + 1];
        p[1] = 1.0;
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        boolean[] seen = new boolean[n + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        seen[1] = true;
        q.offer(1);
        while (t-- > 0) {
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int cur = q.poll();
                    int childNum = 0;
                    for (int v : g[cur]) {
                        if (!seen[v]) {
                            childNum++;
                        }
                    }
                    for (int v : g[cur]) {
                        if (!seen[v]) {
                            seen[v] = true;
                            q.offer(v);
                            p[v] = p[cur] / childNum;
                        }
                    }
                    if (childNum > 0) {
                        p[cur] = 0.0;
                    }
                }
            }
        }
        return p[target];
    }
}
