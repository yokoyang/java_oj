package Algorithm.MyArray;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
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
            else {
                System.out.println(j);
            }
        return i;
    }



}
