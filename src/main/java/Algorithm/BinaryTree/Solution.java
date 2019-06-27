package Algorithm.BinaryTree;


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




    public static void main(String[] args) {
        Solution s = new Solution();
        Integer[] array = new Integer[]{-1, 0, 3, -2, 4, 12, 13, 8};
    }
}
