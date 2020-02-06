package Algorithm.segment_tree;

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

public class SegmentTree {
    int[] nums;
    SegmentTreeNode root;

    SegmentTree(int[] nums) {
        this.nums = nums;
        if (nums.length > 0) {
            root = buildTree(0, nums.length - 1);
        }
    }

    SegmentTreeNode buildTree(int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, nums[start], null, null);
        }
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = buildTree(start, mid);
        SegmentTreeNode right = buildTree(mid + 1, end);
        return new SegmentTreeNode(start, end, left.sum + right.sum, left, right);
    }

    void updateTree(SegmentTreeNode root, int i, int val) {
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
        root.sum = root.left.sum + root.right.sum;
    }

    int sumRange(SegmentTreeNode root, int i, int j) {
        if (i == root.start && j == root.end) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (j <= mid) {
            return sumRange(root.left, i, j);
        } else if (i > mid) {
            return sumRange(root.right, i, j);
        } else {
            return sumRange(root.left, i, mid) + sumRange(root.right, mid + 1, j);
        }
    }
}

class NumArray2 {
    SegmentTree st;

    public NumArray2(int[] nums) {
        st = new SegmentTree(nums);
    }

    public void update(int i, int val) {
        st.updateTree(st.root, i, val);
    }

    public int sumRange(int i, int j) {
        return st.sumRange(st.root, i, j);
    }
}

