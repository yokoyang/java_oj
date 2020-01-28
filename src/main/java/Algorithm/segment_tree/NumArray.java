package Algorithm.segment_tree;

public class NumArray {
    int st[];
    int[] raw_arr;
    int size;

    public NumArray(int[] nums) {
        raw_arr = nums;
        size = nums.length;
        if (size == 0) {
            st = new int[]{0};
            return;
        }
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(nums.length) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new int[max_size]; // Memory allocation

        constructSTUtil(nums, 0, nums.length - 1, 0);
    }

    private int getMid(int i, int j) {
        return i + (j - i) / 2;
    }


    // A recursive function that constructs Segment Tree for array[ss..se].
    // si is index of current node in segment tree st
    int constructSTUtil(int arr[], int ss, int se, int si) {
        // If there is one element in array, store it in current node of
        // segment tree and return
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the sum of values in this node
        int mid = getMid(ss, se);
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }

    public void update(int i, int val) {
        int diff = val - raw_arr[i];
        raw_arr[i] = val;
        updateValueUtil(0, size - 1, i, diff, 0);
    }


    /* A recursive function to update the nodes which have the given
       index in their range. The following are parameters
        st, si, ss and se are same as getSumUtil()
        i    --> index of the element to be updated. This index is in
                 input array.
       diff --> Value to be added to all nodes which have i in range */
    void updateValueUtil(int ss, int se, int i, int diff, int si) {
        // Base Case: If the input index lies outside the range of
        // this segment
        if (i < ss || i > se)
            return;

        // If the input index is in range of this node, then update the
        // value of the node and its children
        st[si] = st[si] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            updateValueUtil(ss, mid, i, diff, 2 * si + 1);
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
        }
    }

    private int getSumUtil(int ss, int se, int qs, int qe, int si) {
        if (qs <= ss && qe >= se) {
            return st[si];
        }
        if (se < qs || ss > qe) {
            return 0;
        }
        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, si * 2 + 1) + getSumUtil(mid + 1, se, qs, qe, si * 2 + 2);
    }

    public int sumRange(int i, int j) {
        return getSumUtil(0, size - 1, i, j, 0);
    }

    public static void main(String[] args) {
        int arr[] = {9, -8};
        NumArray numArray = new NumArray(arr);
        numArray.update(0, 3);

        int t = numArray.sumRange(1, 1);
        System.out.println(t);
        numArray.update(1, 2);
        numArray.update(0, 2);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */