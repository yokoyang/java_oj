package Algorithm;

public class BinarySearchTree {
    private TreeNode tree;

    public TreeNode find(int data) {
        TreeNode p = tree;
        while (p != null) {
            if (data < p.val) {
                p = p.left;
            } else if (data > p.val) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insert(int data) {
        if (tree == null) {
            tree = new TreeNode(data);
            return;
        }

        TreeNode p = tree;
        while (p != null) {
            if (data > p.val) {
                if (p.right == null) {
                    p.right = new TreeNode(data);
                    return;
                }
                p = p.right;
            } else { // val < p.val
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data) {
        TreeNode p = tree; // p 指向要删除的节点，初始化指向根节点
        TreeNode pp = null; // pp 记录的是 p 的父节点
        while (p != null && p.val != data) {
            pp = p;
            if (data > p.val) p = p.right;
            else p = p.left;
        }
        if (p == null) return; // 没有找到

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            TreeNode minP = p.right;
            TreeNode minPP = p; // minPP 表示 minP 的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.val = minP.val; // 将 minP 的数据替换到 p 中
            p = minP; // 下面就变成了删除 minP 了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        TreeNode child; // p 的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }


    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }


    TreeNode kthResult = null;

    private class ResultType {
        // 是否找到
        boolean found;
        // 节点数目
        int val;

        ResultType(boolean found, int val) {
            this.found = found;
            this.val = val;
        }
    }

    private ResultType kthSmallestHelper(TreeNode now, int k) {
        if (now == null) {
            return new ResultType(false, 0);
        }
        ResultType left = kthSmallestHelper(now.left, k);
        if (left.found) {
            return new ResultType(true, left.val);
        }
        if (left.val + 1 == k) {
            kthResult = now;
            return new ResultType(true, now.val);
        }
        ResultType right = kthSmallestHelper(now.right, k - left.val - 1);
        if (right.found) {
            return new ResultType(true, right.val);
        }
        return new ResultType(false, left.val + 1 + right.val);
    }


    public TreeNode kthSmallest(TreeNode root, int k) {
        kthSmallestHelper(root,k);
        return kthResult;
    }
}
