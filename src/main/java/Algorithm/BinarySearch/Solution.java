package Algorithm.BinarySearch;

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
        solution.searchRange(new int[]{1}, 1);
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

    public int[] searchRange(int[] nums, int target) {
        int size = nums.length;
        int[] res = new int[]{-1, -1};
        if (size == 0) {
            return res;
        }
        int l = 0, r = size - 1, mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                int t = mid;
                while (t < size && nums[t] == target) {
                    t++;
                }
                res[1] = t - 1;
                t = mid;
                while (t >= 0 && nums[t] == target) {
                    t--;
                }
                res[0] = t + 1;
                return res;

            }
        }
        return res;
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
        if(rightPart!=null){
            rightPart.left = pRootOfTree;
        }
        return leftPart == null ? pRootOfTree : leftPart;
    }
}
