package Algorithm.BinaryTree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class FindElements {
    TreeNode root = null;

    public FindElements(TreeNode root) {
        root.val = 0;
        recover(root);
        this.root = root;
    }

    private void recover(TreeNode node) {
        if (node.left != null) {
            node.left.val = node.val * 2 + 1;
            recover(node.left);
        }
        if (node.right != null) {
            node.right.val = node.val * 2 + 2;
            recover(node.right);
        }
    }


    public boolean find(int target) {
        if (target < 0) {
            return false;
        }
        return find_C(target, root);
    }

    private boolean find_C(int target, TreeNode now) {
        if (now == null) {
            return false;
        }
        if (now.val == target) {
            return true;
        }
        if (now.val <target) {
            return find_C(target, now.left) || find_C(target, now.right);
        }
        return false;

    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
