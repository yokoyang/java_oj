package Algorithm.BinaryTree;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {


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
//    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
//    假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
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

    //从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            res.add(now.val);
            if (now.left != null) {
                queue.offer(now.left);
            }
            if (now.right != null) {
                queue.offer(now.right);
            }
        }
        return res;
    }

    //按照之字形打印二叉树，即第一行按照从左到右的顺序打印
    // ，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
    public ArrayList<ArrayList<Integer>> zigzagPrint(TreeNode pRoot) {

        int layer = 1;
        //s1存奇数层节点
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(pRoot);
        //s2存偶数层节点
        Stack<TreeNode> s2 = new Stack<>();

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        while (!s1.empty() || !s2.empty()) {
            if (layer % 2 != 0) {
                ArrayList<Integer> temp = new ArrayList<>();
                while (!s1.empty()) {
                    TreeNode node = s1.pop();
                    temp.add(node.val);
                    System.out.print(node.val + " ");
                    if (node.left != null) {
                        s2.push(node.left);
                    }
                    if (node.right != null) {
                        s2.push(node.right);
                    }
                }
                if (!temp.isEmpty()) {
                    res.add(temp);
                    layer++;
                    System.out.println();
                }
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                while (!s2.empty()) {
                    TreeNode node = s2.pop();
                    temp.add(node.val);
                    System.out.print(node.val + " ");
                    if (node.right != null) {
                        s1.push(node.right);
                    }
                    if (node.left != null) {
                        s1.push(node.left);
                    }
                }
                if (!temp.isEmpty()) {
                    res.add(temp);
                    layer++;
                    System.out.println();
                }
            }
        }
        return res;
    }

    //输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    // 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        return IsTreeBST(sequence, 0, sequence.length - 1);
    }

    public boolean IsTreeBST(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int i = start;
        for (; i < end; i++) {
            if (sequence[i] > sequence[end]) {
                break;
            }
        }
        for (int j = i; j < end; j++) {
            if (sequence[j] < sequence[end]) {
                return false;
            }
        }
        return IsTreeBST(sequence, start, i - 1) && IsTreeBST(sequence, i, end - 1);
    }

    //    输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
//    路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
    ArrayList<ArrayList<Integer>> findPathRes = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return findPathRes;
        }
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(root.val);
        findPath_C(root, target, 0, tmp);
        return findPathRes;
    }

    private boolean checkIsLeaf(TreeNode node) {
        if (node.left == null && node.right == null) {
            return true;
        }
        return false;
    }

    private boolean findPath_C(TreeNode now, int target, int nowSum, ArrayList<Integer> record) {
        nowSum += now.val;
        if (checkIsLeaf(now)) {
            if (nowSum == target) {
                findPathRes.add(new ArrayList<>(record));
                return true;
            } else {
                return false;
            }
        }
        if (now.left != null) {
            record.add(now.left.val);
            findPath_C(now.left, target, nowSum, record);
            record.remove(record.size() - 1);
        }
        if (now.right != null) {
            record.add(now.right.val);
            findPath_C(now.right, target, nowSum, record);
            record.remove(record.size() - 1);
        }
        return false;
    }

    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<Integer>();

    //更加精炼的写法，其实是把左右两侧都找完之后，再remove的
    private ArrayList<ArrayList<Integer>> FindPath2(TreeNode root, int target) {
        if (root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<>(list));
        FindPath2(root.left, target);
        FindPath2(root.right, target);
        list.remove(list.size() - 1);
        return listAll;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        Integer[] array = new Integer[]{-1, 0, 3, -2, 4, 12, 13, 8};
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    //    输入一个复杂链表（每个节点中有节点值，以及两个指针，
//    一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。

    public RandomListNode cloneRandomListNode(RandomListNode pHead) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = pHead;
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = pHead;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            cur = cur.next;
        }
        RandomListNode resHead = map.get(pHead);
        cur = pHead;
        while (cur != null) {
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return resHead;
    }

    public RandomListNode cloneRandomListNode2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> record = new HashMap<>();
        RandomListNode cur = pHead;
        while (cur != null) {
            record.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = pHead;
        while (cur != null) {
            record.get(cur).next = record.get(cur.next);
            cur = cur.next;
        }
        cur = pHead;
        while (cur != null) {
            record.get(cur).random = record.get(cur.random);
            cur = cur.next;
        }
        return record.get(pHead);
    }

    public RandomListNode cloneRandomListNode3(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        cloneNodes(pHead);
        cloneRandomLink(pHead);
        return getCloneRandomList(pHead);
    }

    private void cloneNodes(RandomListNode pHead) {
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clonedNode = new RandomListNode(cur.label);
            clonedNode.next = cur.next;
            cur.next = clonedNode;
            cur = cur.next.next;
        }
    }

    private void cloneRandomLink(RandomListNode pHead) {
        RandomListNode cur = pHead;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
    }

    private RandomListNode getCloneRandomList(RandomListNode pHead) {
        RandomListNode curNode = pHead;
        RandomListNode cloneHead = pHead.next;
        while (curNode != null) {
            RandomListNode cloneNode = curNode.next;
            curNode.next = curNode.next.next;
            if (cloneNode.next != null) {
                cloneNode.next = cloneNode.next.next;
            }
            curNode = curNode.next;
        }
        return cloneHead;
    }

    //二叉树的序列化和反序列化
    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val).append(",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }

    TreeNode Deserialize(String str) {
        if (str == null) {
            return null;
        }
        String[] strSeg = str.split(",");
        return DeserializeStr(strSeg);
    }

    int strSegIndex = -1;

    TreeNode DeserializeStr(String[] strSeg) {
        strSegIndex++;
        TreeNode treeNode = null;
        if (!strSeg[strSegIndex].equals("#")) {
            treeNode = new TreeNode(Integer.parseInt(strSeg[strSegIndex]));
            treeNode.left = DeserializeStr(strSeg);
            treeNode.right = DeserializeStr(strSeg);
        }
        return treeNode;
    }
}
