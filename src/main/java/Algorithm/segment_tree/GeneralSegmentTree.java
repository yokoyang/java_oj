package Algorithm.segment_tree;


public class GeneralSegmentTree {
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

