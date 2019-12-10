package contest;

import java.lang.reflect.Array;
import java.util.*;

public class Week166 {
    public int subtractProductAndSum(int n) {
        int mut = 1;
        int t1 = Math.abs(n);

        int sum = 0;
        int t2;
        while (t1 > 0) {
            t2 = t1 % 10;
            mut *= t2;
            t1 /= 10;
            sum += t2;
        }
        if (n < 0) {
            mut *= -1;
            sum *= -1;
        }
        return mut - sum;
    }
//Example 1:
//
//Input: groupSizes = [3,3,3,3,3,1,3]
//Output: [[5],[0,1,2],[3,4,6]]
//Explanation:
//Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
//Example 2:
//
//Input: groupSizes = [2,1,3,3,3,2]
//Output: [[1],[0,5],[2,3,4]]

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> record = new HashMap<>();
        int p = 0;
        for (int size : groupSizes) {
            ArrayList<Integer> posList = record.getOrDefault(size, new ArrayList<>());
            posList.add(p);
            record.put(size, posList);
            p++;
        }
        for (Map.Entry entry : record.entrySet()) {
            int key = (int) entry.getKey();
            ArrayList<Integer> pos = (ArrayList<Integer>) entry.getValue();
            int size = pos.size();
            int index = 0;
            while (size > 0) {
                size -= key;
                ArrayList<Integer> t = new ArrayList<>();
                for (int i = 0; i < key; i++, index++) {
                    t.add(pos.get(index));
                }
                res.add(t);
            }
        }
        return res;
    }
    public List<List<Integer>> groupThePeople2(int[] arr) {
        int n = arr.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int curr = arr[i];
            List<Integer> temp = new ArrayList<>();
            if(map.containsKey(curr)) {
                temp = map.get(curr);
            }
            temp.add(i);
            map.put(curr, temp);
            if(temp.size() == curr){
                ans.add(temp);
                map.remove(curr);
            }
        }

        return ans;

    }
    //    Input: nums = [1,2,5,9], threshold = 6
    //Output: 5
    //Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
    //If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
    //Example 2:
    //Input: nums = [2,3,5,7,11], threshold = 11
    //Output: 3
    //Example 3:
    //
    //Input: nums = [19], threshold = 5
    //Output: 4
    public int smallestDivisor(int[] A, int threshold) {
        Arrays.sort(A);
        int left = 1, right = A[A.length - 1], m = 1, sum = 0;
        while (left <= right) {
            m = (left + right) / 2;
            sum = 0;
            for (int i : A)
                sum += upDivide(i, m);
            if (sum > threshold)
                left = m + 1;
            else
                right = m - 1;
        }
        return left;
    }


    int upDivide(int n, int m) {
        double c = (double) n / (double) m;
        return (int) Math.ceil(c);
    }


    //    1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
    public int minFlips(int[][] mat) {
        int res;
        int m = mat.length;
        int n = mat[0].length;
        res = bfsMinFlips(mat, m, n);
        return res;
    }

    int[] dx = new int[]{1, -1, 0, 0,};
    int[] dy = new int[]{0, 0, 1, -1};

    //    这种需要判断是否存在的，一定要编码
    private String convertMatrix(int[][] mat) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                sb.append(mat[i][j]);
            }
        }
        return sb.toString();
    }

    private int bfsMinFlips(int[][] mat, int m, int n) {
        int step = 0;
        LinkedList<int[][]> pq = new LinkedList<>();
        if (checkZero(mat)) {
            return step;
        }
        //矩阵状态是否已经判断过
        HashSet<String> hs = new HashSet<>();
        pq.add(mat);
        hs.add(convertMatrix(mat));
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int s = 0; s < size; s++) {
                int[][] now = pq.poll();
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int[][] copy = deepCopy(now);
                        transform(copy, i, j);
                        if (hs.contains(convertMatrix(copy))) {
                            continue;
                        }
                        if (checkZero(copy)) {
                            return step + 1;
                        }
                        hs.add(convertMatrix(copy));
                        pq.offer(copy);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private int[][] deepCopy(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            res[i] = Arrays.copyOf(mat[i], n);
        }
        return res;
    }

    private boolean checkZero(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean transform(int[][] mat, int x, int y) {
        if (x < 0 || y < 0 || x >= mat.length || y >= mat[0].length) {
            return false;
        }
        mat[x][y] ^= 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX >= 0 && newX < mat.length && newY >= 0 && newY < mat[0].length) {
                mat[newX][newY] ^= 1;
            }
        }
        return true;
    }


    //    编码的方法
    private static final int[] d = {0, 0, 1, 0, -1, 0};

    public int minFlips2(int[][] mat) {
        int start = 0, m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                start |= mat[i][j] << (i * n + j); // convert the matrix to an int.
        Queue<Integer> q = new LinkedList<>(Arrays.asList(start));
        Set<Integer> seen = new HashSet<>(q);
        for (int step = 0; !q.isEmpty(); ++step) {
            for (int sz = q.size(); sz > 0; --sz) {
                int cur = q.poll();
                if (cur == 0) // All 0s matrix found.
                    return step;
                for (int i = 0; i < m; ++i) { // traverse all m * n bits of cur.
                    for (int j = 0; j < n; ++j) {
                        int next = cur;
                        for (int k = 0; k < 5; ++k) { // flip the cell (i, j) and its neighbors.
                            int r = i + d[k], c = j + d[k + 1];
                            if (r >= 0 && r < m && c >= 0 && c < n)
                                next ^= 1 << r * n + c;
                        }
                        if (seen.add(next)) // seen it before ?
                            q.offer(next); // no, put it into the Queue.
                    }
                }
            }
        }
        return -1; // impossible to get all 0s matrix.
    }

    public static void main(String[] args) {
        Week166 week166 = new Week166();
        System.out.println(week166.minFlips2(new int[][]{{1, 0, 0}, {1, 0, 0}}));
        System.out.println(week166.minFlips(new int[][]{{1, 0, 0}, {1, 0, 0}}));
        System.out.println(week166.smallestDivisor(new int[]{962551, 933661, 905225, 923035, 990560}, 10));
        System.out.println(week166.groupThePeople(new int[]{2, 1, 3, 3, 3, 2}));
//        week166.subtractProductAndSum(114);
    }
}
