package Algorithm.BinarySearch;

import java.util.*;

public class Solution {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        System.out.println((str1 == str2));
        System.out.println((str2 == str3));
        System.out.println((str1 == str3));

        String a = new String("abc").intern();
        String b = new String("abc").intern();

        if (a == b) {
            System.out.println("a==b");
        }

        int[] a1 = new int[]{1, 3};
        int[] a2 = new int[]{2};
//        int[] a1 = new int[]{-1, 1, 3, 5, 7, 9};
//        int[] a2 = new int[]{2, 4, 6, 8, 10, 12, 14, 16};

        double t = solution.findMedianSortedArrays2(a1, a2);
        System.out.println(t);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode head = sortedArrayToBST_C(nums, 0, nums.length - 1);
        return head;
    }

    TreeNode sortedArrayToBST_C(int[] nums, int s, int e) {
        if (s > e) {
            return null;
        }
        int mid = s + (e - s) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST_C(nums, s, mid - 1);
        node.right = sortedArrayToBST_C(nums, mid + 1, e);
        return node;
    }

    //34. Find First and Last Position of Element in Sorted Array
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }

            if (nums[mid] == target) {
                if ((mid > 0 && nums[mid - 1] != target) || mid == 0) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        return idx;
    }

    private int findLast(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }
            if (nums[mid] == target) {
                if ((mid < nums.length - 1 && nums[mid + 1] != target) || mid == nums.length - 1) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            }
        }
        return idx;
    }

    //    LeetCode 4

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);

        int k = (n1 + n2 + 1) / 2;
        int l = 0;
        int r = n1;

        while (l < r) {
            int m1 = l + (r - l) / 2;
            int m2 = k - m1;
            System.out.println(m1);
            if (nums1[m1] < nums2[m2 - 1])
                l = m1 + 1;
            else
                r = m1;
        }
        int m1 = l;
        int m2 = k - l;

        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

        if ((n1 + n2) % 2 == 1)
            return c1;

        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

        return (c1 + c2) * 0.5;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        int k = (n1 + n2 + 1) / 2;
        // n1<=n2
        int m1 = 0, m2 = 0;
        int l = 0, r = n1;
        int mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            m2 = k - mid;
            if (nums1[mid] < nums2[m2 - 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        m1 = l;
        m2 = k - m1;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

        if ((n1 + n2) % 2 == 1)
            return c1;

        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

        return (c1 + c2) * 0.5;

    }

    //    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
    //    要求不能创建任何新的结点，只能调整树中结点指针的指向。
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        if (pRootOfTree.left == null && pRootOfTree.right == null) {
            return pRootOfTree;
        }

        TreeNode leftPart = Convert(pRootOfTree.left);
        TreeNode now = leftPart;
        while (now != null && now.right != null) {
            now = now.right;
        }
        if (now != null) {
            now.right = pRootOfTree;
            pRootOfTree.left = now;
        }
        TreeNode rightPart = Convert(pRootOfTree.right);
        pRootOfTree.right = rightPart;
        if (rightPart != null) {
            rightPart.left = pRootOfTree;
        }
        return leftPart == null ? pRootOfTree : leftPart;
    }

    //剑指offer 0~n-1的递增数组中缺失的数字，
    // 长度是n-1 每个数字的范围在0~n-1之间
    public int getMissingNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int s = 0, end = nums.length, mid = -1;
        while (s <= end) {
            mid = (s + end) >> 1;
            if (nums[mid] == mid) {
                s = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] == mid - 1) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        //如果是末尾缺失
        if (s == nums.length) {
            return nums.length;
        }
        return mid;
    }

    //类似的思路
    //数组单调递增，每个元素都是整数而且唯一，找到任意一个数值=下标的元素
    public int getNumberSameAsIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == mid) {
                return mid;
            }
            if (nums[mid] > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    //剑指offer 二叉树的第k大数
    //也就是中序遍历的第k个
    public TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) {
            return null;
        }
        TreeNode target = null;
        if (pRoot.left != null) {
            target = KthNode(pRoot.left, k);
        }
        if (target == null) {
            if (k == 1) {
                return pRoot;
            }
            k--;
        }
        if (target == null && pRoot.right != null) {
            target = KthNode(pRoot.right, k);
        }
        return target;
    }

    //二叉树的深度
    public int TreeDepth(TreeNode pRoot) {
        if (pRoot == null) {
            return 0;
        }
        return Math.max(TreeDepth(pRoot.left), TreeDepth(pRoot.right)) + 1;
    }

    //借用之前求二叉树高度的函数，缺点是每个点会遍历多次
    public boolean isBalanced_1(TreeNode root) {
        if (root == null) {
            return true;
        }
        int l = TreeDepth(root.left);
        int r = TreeDepth(root.right);
        if (Math.abs(l - r) > 1) {
            return false;
        }
        return isBalanced_1(root.left) && isBalanced_1(root.right);
    }


    //以后序遍历的方式，边遍历，边记录
    public boolean isBalanced_2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalanced_2_core(root, new int[]{0});
    }

    private boolean isBalanced_2_core(TreeNode root, int[] depth) {
        if (root == null) {
            depth[0] = 0;
            return true;
        }
        int[] l_depth = new int[1];
        int[] r_depth = new int[1];
        if (isBalanced_2_core(root.left, l_depth) && isBalanced_2_core(root.right, r_depth)) {
            depth[0] = Math.max(l_depth[0], r_depth[0]) + 1;
            if (Math.abs(l_depth[0] - r_depth[0]) <= 1) {
                return true;
            }
        }
        return false;
    }


}
