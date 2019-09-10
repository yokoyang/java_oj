package zhaohang;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> inorderTraversal3(TreeNode root) {
        if (root == null) return ans;
        inorderTraversal(root.left);
        ans.add(root.val);
        return inorderTraversal(root.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // method 1: recursion

        helper(root, res);
        return res;
    }

    //helper function for method 1
    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }

    TreeNode prev;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (prev != null && prev.val >= root.val) {
            return false;
        }
        prev = root;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
    public class CloneExample {
        private int a;
        private int b;
    }


}
