package Algorithm.MyArray;

import java.util.*;
import java.util.stream.Collectors;

import static Algorithm.sort.QuickSort.partition;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.MoreThanHalfNum_Solution_1(new int[]{4, 2, 1, 4, 2, 4});
        s.printMatrix(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        s.reOrderArrayOddFirst(new int[]{1, 2, 3, 4, 5, 6, 7});
        s.sortAges(new int[]{2, 31, 2, 1, 3});
        s.findIncreaseMatrix(7, new int[][]{{1, 2, 8, 9}, {4, 7, 10, 13}});
        System.out.println(s.findOneDuplicate(new int[]{2, 3, 1, 0, 2, 5, 3}));
        System.out.println(s.findOneDuplicateNotModify(new int[]{2, 3, 1, 0, 2, 5, 3}));
        int[] t0 = s.plusOne(new int[]{9});
        HashSet<Integer> hashSet = new HashSet<>();
        boolean r1 = hashSet.add(1);
        boolean r2 = hashSet.add(1);
        System.out.println(r1);
        System.out.println(r2);
        int[][] t1 = new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};

        int[][] r = s.merge(t1);
        System.out.println(r);
        s.sortedSquares(new int[]{-2, 0});
        int[] t = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(s.removeDuplicates(t));
        for (int i : t) {
            System.out.print(i);
        }
    }

    private class Student {
        private String sex;
        private int Height;

        public int getHeight() {
            return Height;
        }

        public String getSex() {
            return sex;
        }
    }

    private void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int tail = m + n - 1;
        while (j >= 0) {
            nums1[tail--] = (i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }
    }

    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][size - j - 1];
                matrix[i][size - j - 1] = tmp;
            }
        }
    }

    public static void remove(ArrayList<String> list2, List<Student> studentsList) {
        list2.removeIf(str -> str.equals("b"));
        Map<String, List<Student>> stuMap = new HashMap<String, List<Student>>();
        for (Student stu : studentsList) {
            if (stu.getHeight() > 160) { // 如果身高大于 160
                List<Student> a = stuMap.getOrDefault(stu.getSex(), new ArrayList<>());
                a.add(stu);
            }
        }
        Map<String, List<Student>> stuMap2 = studentsList.stream().filter((Student s) -> s.getHeight() > 160)
                .collect(Collectors.groupingBy(Student::getSex));

        List<String> names = Arrays.asList(" 张三 ", " 李四 ", " 王老五 ", " 李三 ", " 刘老四 ", " 王小二 ", " 张四 ", " 张五六七 ");

        String maxLenStartWithZ = names.stream()
                .parallel()
                .filter(name -> name.startsWith(" 张 "))
                .mapToInt(String::length)
                .max()
                .toString();

    }

    //Input: [[1,3],[2,6],[8,10],[15,18]]
    //Output: [[1,6],[8,10],[15,18]]
    //Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
    public int[][] merge2(int[][] intervals) {
        List<List<Integer>> res = new ArrayList<>();
        int size = intervals.length;
        if (size == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        if (size == 1) {
            return intervals;
        }
        res.add(Arrays.asList(intervals[0][0], intervals[0][1]));
        for (int i = 1; i < size; i++) {
            if (intervals[i][0] <= res.get(res.size() - 1).get(1)) {
                List<Integer> last = res.get(res.size() - 1);
                res.set(res.size() - 1, Arrays.asList(last.get(0), Math.max(res.get(res.size() - 1).get(1), intervals[i][1])));
                continue;
            }
            res.add(Arrays.asList(intervals[i][0], intervals[i][1]));
        }
        int[][] arrayRes = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            arrayRes[i][0] = res.get(i).get(0);
            arrayRes[i][1] = res.get(i).get(1);
        }
        return arrayRes;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    //    https://zhanghuimeng.github.io/post/leetcode-977-squares-of-a-sorted-array/
    public int[] sortedSquares(int[] A) {
        int size = A.length;
        if (size == 0) {
            return A;
        }
        if (size == 1) {
            return new int[]{A[0] * A[0]};
        }
        int min = Integer.MAX_VALUE;
        int minPos = -1;
        for (int i = 0; i < size; i++) {
            int t = Math.abs(A[i]);
            if (t < min) {
                min = t;
                minPos = i;
            }
        }
        int[] ans = new int[size];
        int left = minPos;
        int right = minPos + 1;
        int now = 0;
        int tl;
        int tr;
        while ((left >= 0) && (right < size)) {
            tl = A[left] * A[left];
            tr = A[right] * A[right];
            if (tl < tr) {
                ans[now] = tl;
                left--;
            } else {
                ans[now] = tr;
                right++;
            }
            now++;
        }
        while (right < size) {
            ans[now++] = A[right] * A[right];
            right++;
        }
        while (left >= 0) {
            ans[now++] = A[left] * A[left];
            left--;
        }
        return ans;
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> record = new HashMap<>();
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int now = record.getOrDefault(nums[i], 0);
            now++;
            record.put(nums[i], now);
        }
        int max = 0;
        int k = -1;
        List<Integer> kList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : record.entrySet()) {
            int v = entry.getValue();
            if (v > max) {
                max = v;
                k = entry.getKey();
                kList.clear();
                kList.add(k);
            } else if (v == max) {
                k = entry.getKey();
                kList.add(k);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int k2 : kList) {
            int s = 0;
            int e = 0;
            boolean visit = false;
            for (int i = 0; i < size; i++) {
                if (nums[i] == k2) {
                    if (!visit) {
                        s = i;
                        visit = true;
                    }
                    e = i;
                }
            }
            if (e - s < result) {
                result = e - s;
            }
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++)
            if (i < 2 || nums[j] > nums[i - 2])
                nums[i++] = nums[j];
        return i;
    }

    public void rotate(int[] nums, int k) {
        int size = nums.length;
        int[] res = new int[size];
        for (int i = 0; i < nums.length; i++) {
            res[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }

    public boolean containsUnique(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        if (res == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x : nums) {
            if (set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }

    //    可以修改数组的前提下，找到任意一个数组中的重复数字
    //长度为n，所有的数字都在0-n-1内，找到任意一个重复数字
    public int findOneDuplicate(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
        }
        return -1;
    }


    //    不可以修改数组的前提下，找到任意一个数组中的重复数字
    //长度为n+1，所有的数字都在1-n内，找到任意一个重复数字
    public int findOneDuplicateNotModify(int[] nums) {
        int size = nums.length;
        int start = 1;
        int end = size - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int count = rangeFindCount(nums, start, mid);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > mid - start + 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    private int rangeFindCount(int[] nums, int start, int end) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }

    // Offer Q4
    //二维数组，每一行从左往右递增，每一列从上到下递增
    //在这个二维数组中查询一个数字
    public boolean findIncreaseMatrix(int target, int[][] array) {
        int m = array.length;
        if (m == 0) {
            return false;
        }
        int n = array[0].length;
        int nowCol = n - 1;
        int nowRow = 0;
        while (nowCol >= 0 && nowRow < m) {
            if (array[nowRow][nowCol] == target) {
                return true;
            }
            if (array[nowRow][nowCol] > target) {
                nowCol--;
            } else {
                nowRow++;
            }
        }
        return false;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] res2 = new int[res.size()];
        for (int ii = 0; ii < res.size(); ii++) {
            res2[ii] = res.get(ii);
        }
        return res2;
    }

    public int[] plusOne(int[] digits) {
        int carry = 1;
        int size = digits.length;
        if (size == 1 && digits[0] == 0) {
            return new int[]{1};
        }
        List<Integer> res = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            if (digits[i] + carry == 10) {
                res.add(0);
                carry = 1;
            } else {
                res.add(digits[i] + carry);
                carry = 0;
            }
        }
        if (carry == 1) {
            res.add(1);
        }
        int[] res2 = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            res2[i] = res.get(res.size() - 1 - i);
        }
        return res2;
    }

    public void moveZeroes(int[] nums) {
        int size = nums.length;
        int pos = 0;
        for (int i = 0; i < size; i++) {
            if (nums[i] != 0) {
                nums[pos++] = nums[i];
            }
        }
        for (; pos < size; pos++) {
            nums[pos] = 0;
        }
    }

    //O(n) 复杂度对公司员工年龄排序
    public void sortAges(int[] ages) {
        int size = ages.length;
        if (size <= 1) {
            return;
        }
        int oldestAge = 99;
        int[] timesOfAge = new int[oldestAge + 1];
        for (int i = 0; i < size; i++) {
            timesOfAge[ages[i]]++;
        }
        int index = 0;
        for (int i = 0; i <= oldestAge; i++) {
            for (int j = 0; j < timesOfAge[i]; j++) {
                ages[index] = i;
                index++;
            }
        }
    }

    //返回旋转数组中的最小元素
    public int minNumberInRotateArray(int[] array) {
        int size = array.length;
        if (size <= 1) {
            return size;
        }
        int index1 = 0;
        int index2 = size - 1;
        int mid;
        while (array[index1] >= array[index2]) {
            if (index1 + 1 == index2) {
                return array[index2];
            }
            mid = index1 + (index2 - index1) / 2;
            if (array[mid] >= array[index1]) {
                index1 = mid;
            } else if (array[mid] <= array[index2]) {
                index2 = mid;
            }
            if (array[index1] == array[index2] && array[index1] == array[mid]) {
                int minVal = array[index1];
                for (int i = index1; i <= index2; i++) {
                    minVal = Math.min(array[i], minVal);
                }
                return minVal;
            }
        }
        return array[index1];
    }

    private boolean isOdd(int num) {
        return (num & 1) == 1;
    }

    //重新排列数组，使得奇数在前面
    //相对位置可以改变
    public void reOrderArrayOddFirst(int[] array) {
        if (array.length < 2) {
            return;
        }
        int s = 0, p = array.length - 1;
        while (s != p) {
            if (!isOdd(array[s])) {
                while (!isOdd(array[p])) {
                    p--;
                }
                swap(array, s, p);
            }
            s++;
        }
    }

    public void reOrderArrayKeepRelativeOrder(int[] array) {
        //相对位置不变，稳定性
        //插入排序的思想
        int m = array.length;
        int k = 0;
        //记录已经摆好位置的奇数的个数
        for (int i = 0; i < m; i++) {
            if (isOdd(array[i])) {
                int j = i;
                while (j > k) {//j >= k+1
                    swap(array, j, j - 1);
                    j--;
                }
                k++;
            }
        }
    }

    //    输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
    ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int start = 0;
        while (rows > start * 2 && columns > start * 2) {
            printMatrixInCircle(matrix, rows, columns, start);
            start++;
        }
        return list;
    }

    public void printMatrixInCircle(int[][] matrix, int rows, int columns, int start) {
        // 从左到右打印一行
        for (int i = start; i < columns - start; i++)
            list.add(matrix[start][i]);
        // 从上到下打印一列
        for (int j = start + 1; j < rows - start; j++)
            list.add(matrix[j][columns - start - 1]);
        // 从右到左打印一行
        for (int m = columns - start - 2; m >= start && rows - start - 1 > start; m--)
            list.add(matrix[rows - start - 1][m]);
        // 从下到上打印一列
        for (int n = rows - start - 2; n >= start + 1 && columns - start - 1 > start; n--)
            list.add(matrix[n][start]);
    }

    //offer 39
    // 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    //version 1: 可以修改原始数组的情况下
    public int MoreThanHalfNum_Solution_1(int[] array) {
        int length = array.length;
        int mid = length >> 1;
        int start = 0;
        int end = length - 1;
        int index = partition(array, 0, end);
        while (index != mid) {
            if (index > mid) {
                end = index - 1;
                index = partition(array, start, end);
            } else {
                start = index + 1;
                index = partition(array, start, end);
            }
        }
        int result = array[mid];
        int times = 0;
        for (int i = 0; i < length; ++i) {
            if (array[i] == result)
                times++;
        }
        if (times * 2 <= length) {
            return 0;
        } else {
            return result;
        }
    }

    //不能修改原始数组的情况下，找到数组中出现次数超过一半的元素
    //记录每个元素出现的次数
    public int MoreThanHalfNum_Solution_2(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int res = array[0];
        int times = 1;
        for (int i = 1; i < array.length; i++) {
            if (times == 0) {
                times = 1;
                res = array[i];
            } else if (array[i] == res) {
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for (int i = 0; i < array.length; i++) {
            if (res == array[i]) {
                times++;
            }
        }
        if (times * 2 <= array.length) {
            return 0;
        }
        return res;
    }
}
