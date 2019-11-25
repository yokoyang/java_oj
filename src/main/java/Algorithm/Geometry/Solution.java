package Algorithm.Geometry;

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
}
