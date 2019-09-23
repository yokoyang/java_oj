package Algorithm.BinaryTree;


import java.util.*;

public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    TreeNode result;
    int last;

    private int traceFind2(TreeNode now, TreeNode p, TreeNode q) {
        int findNum = 0;
        if (now == null) {
            return findNum;
        }
        if (now == p) {
            findNum++;
            last = p.val;
        } else if (now == q) {
            findNum++;
            last = q.val;
        }
        int left = 0;
        int right = 0;
        if (result == null) {
            if (now.left != null) {
                left = traceFind2(now.left, p, q);
            }
            if (now.right != null) {
                right = traceFind2(now.right, p, q);
            }
            if (left == 1 && right == 1) {
                result = now;
            } else if (findNum == 1) {
                if (left + right == 1) {
                    result = now;
                }
            }
            if (left == 2 || right == 2) {
                if (result != null) {
                    if (last == p.val) {
                        result = q;
                    } else if (last == q.val) {
                        result = p;
                    }
                }

            }
            findNum += left;
            findNum += right;
        }

        return findNum;
    }

    public TreeNode lowestCommonAncestor(TreeNode now, TreeNode p, TreeNode q) {
        if (now == null || now == p || now == q) return now;
        TreeNode left = lowestCommonAncestor(now.left, p, q);
        TreeNode right = lowestCommonAncestor(now.right, p, q);
        if (left == null) return right;
        else if (right == null) return left;
        else return now;
    }
    //    101. Symmetric Tree
    //    Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
    //For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    //    105. Construct Binary Tree from Preorder and Inorder Traversal
    //    preorder = [3,9,20,15,7]
    //inorder = [9,3,15,20,7]
    //Return the following binary tree:
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    Map<Integer, Integer> posRecord = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            posRecord.put(inorder[i], i);
        }
        int preStart = 0, inStart = 0;
        return buildTreeTrace(preorder, preStart, inStart, inorder.length - 1);
    }

    private TreeNode buildTreeTrace(int[] preorder, int preStart, int inStart, int inEnd) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootPos;
        rootPos = posRecord.get(root.val);
        root.left = buildTreeTrace(preorder, preStart + 1, inStart, rootPos - 1);
        //        rootPos - inStart是root的左子树大小
        root.right = buildTreeTrace(preorder, preStart + rootPos - inStart + 1, rootPos + 1, inEnd);
        return root;
    }



    public static void main(String[] args) {
        Solution s = new Solution();
        s.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        Integer[] array = new Integer[]{-1, 0, 3, -2, 4, 12, 13, 8};
    }
}
