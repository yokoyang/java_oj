package Algorithm.Geometry;

import java.util.Arrays;

public class Solution {
    //    1266. Minimum Time Visiting All Points
    public int minTimeToVisitAllPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < points.length - 1; i++) {
            sum += AAndBDis(points[i], points[i + 1]);
        }
        return sum;
    }

    private int AAndBDis(int[] A, int[] B) {
        int x1 = A[0];
        int x2 = B[0];
        int y1 = A[1];
        int y2 = B[1];
        int xd = Math.abs(x1 - x2);
        int yd = Math.abs(y1 - y2);
        return Math.max(yd, xd);
    }

    //    1232. Check If It Is a Straight Line
//You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkStraightLine(new int[][]{{-7,4},{-11,-5},{3,-10},{8,-10},{-3,0},{11,0},{-1,10},{4,3},{-4,-6},{-2,-7},{4,11}}));
    }

    public boolean checkStraightLine(int[][] coordinates) {
        boolean res = true;
        Arrays.sort(coordinates, (a, b) -> (a[0] - b[0]));
        double k = 0, preK = 0;
        for (int i = 1; i < coordinates.length; i++) {
            double preX = coordinates[i - 1][0];
            double preY = coordinates[i - 1][1];

            double X = coordinates[i][0];
            double Y = coordinates[i][1];
            if ((preY - Y) == 0) {
                k = 0;
            } else {
                preK = k;
                k = (preX - X) / (preY - Y);
                if (k != preK && i != 1) {
                    return false;
                }
            }
        }
        return res;
    }
}
