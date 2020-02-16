package contest;


//class ProductOfNumbers {
//    ArrayList<Integer> nums;
//    int[] dp;
//    int size ;
//
//    public ProductOfNumbers() {
//        dp = new int[101];
//        size = 0;
//        nums = new ArrayList<>();
//        dp[0] = 1;
//    }
//
//    public void add(int num) {
//        nums.add(num);
//        size++;
//        dp[size] = dp[size - 1] * num;
//    }
//
//    public int getProduct(int k) {
//        nums.subList()
//        System.out.println(dp[size] / dp[size - k + 1]);
//        return dp[size] / dp[size - k + 1];
//    }
//}

import com.google.common.base.Strings;

import java.util.*;

class SegmentTreeNode {
    int start, end, sum;
    SegmentTreeNode left, right;

    SegmentTreeNode(int start, int end, int sum, SegmentTreeNode left, SegmentTreeNode right) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.left = left;
        this.right = right;
    }
}

class GeneralSegmentTree {
    public int[] nums;
    public SegmentTreeNode root;

    public int func(int a, int b) {
        return a * b;
    }

    public GeneralSegmentTree(int[] nums) {
        this.nums = nums;
        if (nums.length > 0) {
            root = buildTree(0, nums.length - 1);
        }
    }

    public SegmentTreeNode buildTree(int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, nums[start], null, null);
        }
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = buildTree(start, mid);
        SegmentTreeNode right = buildTree(mid + 1, end);
        return new SegmentTreeNode(start, end, func(left.sum, right.sum), left, right);
    }

    public void updateTree(SegmentTreeNode root, int i, int val) {
        if (root.start == i && root.end == i) {
            root.sum = val;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (i <= mid) {
            updateTree(root.left, i, val);
        } else {
            updateTree(root.right, i, val);
        }
        root.sum = func(root.left.sum, root.right.sum);
    }

    public int sumRange(SegmentTreeNode root, int i, int j) {
        if (i == root.start && j == root.end) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (j <= mid) {
            return sumRange(root.left, i, j);
        } else if (i > mid) {
            return sumRange(root.right, i, j);
        } else {
            return func(sumRange(root.left, i, mid), sumRange(root.right, mid + 1, j));
        }
    }
}

class ProductOfNumbers {
    //    ArrayList<Integer> nums;
    int size = 0;
    GeneralSegmentTree generalSegmentTree;

    public ProductOfNumbers() {
//        nums = new ArrayList<>();
        int[] tmp = new int[40000];
        Arrays.fill(tmp, 1);
        generalSegmentTree = new GeneralSegmentTree(tmp);
//         dp[0] = 1;
    }

    public void add(int num) {
//        nums.add(num);
        generalSegmentTree.updateTree(generalSegmentTree.root, size, num);
        size++;
        // dp[size] =dp[size-1]*num;
    }

    public int getProduct(int k) {
        int t = generalSegmentTree.sumRange(generalSegmentTree.root, size - k, size - 1);
//        System.out.println(t);
        return t;
        // return dp[size]/dp[size-k];
    }
}

public class Week176 {
    public static void main(String[] args) {
        Week176 week176 = new Week176();
//        week176.isPossible(new int[]{8, 5});
        // create map
        Map<String, String> map = new TreeMap<>((a, b) -> {
            return (b.compareTo(a));
        });

        // populate the map
        map.put("1", "TP");
        map.put("3", "BEST");
        map.put("2", "IS");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        // create a synchronized map
//
//
//        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
//        productOfNumbers.add(3);        // [3]
//        productOfNumbers.add(0);        // [3,0]
//        productOfNumbers.add(2);        // [3,0,2]
//        productOfNumbers.add(5);        // [3,0,2,5]
//        productOfNumbers.add(4);        // [3,0,2,5,4]
//        productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
//        productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
//        productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
//        productOfNumbers.add(8);        // [3,0,2,5,4,8]
//        productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32
    }

    String getKey(int[] arr, int s) {
        StringBuilder sb = new StringBuilder();
        for (int v : arr) {
            sb.append(v).append(",");
        }
        sb.append(s);
        return sb.toString();
    }

    public boolean isPossible(int[] target) {
        long sum = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(b, a);
        });
        for (int i = 0; i < target.length; i++) {
            sum += target[i];
            pq.offer((long) target[i]);
        }
        while (!pq.isEmpty()) {
            long now = pq.poll();
            // if there is no number bigger than 1 early exit true
            if (now == 1) {
                return true;
            }
            // check if current max can be subtract multiple times by other elements' sum.
            long diff = sum - now;
            long times = now / diff;

            // check if current max can be subtract at least once by other elements' sum.
            // this also ensures that no number less than 1 will be added to the priority queue.
            if (times < 1) {
                return false;
            }

            // update value.
            sum = sum - times * diff;
            now = now % diff;
            pq.offer(now);
        }
        return true;
    }
}
