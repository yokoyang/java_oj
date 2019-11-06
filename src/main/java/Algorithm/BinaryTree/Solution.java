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


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return traceBackFindAncestor(root, p, q);
    }

    private TreeNode traceBackFindAncestor(TreeNode now, TreeNode p, TreeNode q) {
        if (now == null || now == p || now == q) {
            return now;
        }
        TreeNode left = traceBackFindAncestor(now.left, p, q);
        TreeNode right = traceBackFindAncestor(now.right, p, q);
        if (left != null && right != null) {
            return now;
        }
        if (left == null) {
            return right;
        } else {
            return left;
        }
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
//    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
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

    //二叉树的下一个节点
    //给定一个二叉树和其中一个节点，找到中序遍历的下一个节点
    //有左右父指针
    //分析：如果这个节点有右子树，next=右子树的最左节点
    //如果没有右子树，如果节点是父亲的左节点，next=父亲的右节点，如果节点是父亲的右节点，next=递归(父亲)
    public BinaryTreeNode getNextInorderNode(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        BinaryTreeNode pNext = null;
        if (node.right != null) {
            BinaryTreeNode pRight = node.right;
            while (pRight.left != null) {
                pRight = pRight.right;
            }
            pNext = pRight;
        } else if (node.parent != null) {
            BinaryTreeNode pCurrent = node;
            BinaryTreeNode pParent = node.parent;
            while (pParent != null && pCurrent == pParent.right) {
                pCurrent = pParent;
                pParent = pParent.parent;
            }
            pNext = pParent;
        }
        return pNext;
    }


    class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode parent;

        BinaryTreeNode(int v) {
            this.val = v;
        }
    }

    //输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return false;
        if (root1 == null) return false;
        boolean flag = false;
        if (root1.val == root2.val) {
            flag = isSubTree(root1, root2);
        }
        if (!flag) {
            flag = HasSubtree(root1.left, root2);
            if (!flag) {
                flag = HasSubtree(root1.right, root2);
            }
        }
        return flag;
    }

    private boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val == root2.val) {
            return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    //输入一个二叉树，将它转变为它的镜像
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        Mirror(root.left);
        Mirror(root.right);
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        Integer[] array = new Integer[]{-1, 0, 3, -2, 4, 12, 13, 8};
    }
}
