package Algorithm.division;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinDis {
    public static void main(String[] args) {
        // 测试用例
        Point[] points = new Point[7];

        points[0] = new Point(1, 1);
        points[1] = new Point(1, 9);
        points[2] = new Point(2, 5);
        points[3] = new Point(3, 1);
        points[4] = new Point(4, 4);
        points[5] = new Point(5, 8);
        points[6] = new Point(6, 2);

        // 预处理，基于x轴坐标排序，便于分治法实施
        Arrays.sort(points, (p1, p2) -> Integer.compare(p1.x, p2.x));
        // 测试
        System.out.println(divide(0, points.length - 1, points));
    }

    public static double divide(int left, int right, Point[] points) {
        double curMinDis = Double.MAX_VALUE;
        if (left >= right) {
            return curMinDis;
        }
        // two point
        if (left + 1 == right) {
            return distance(points[left], points[right]);
        }
        int mid = (left + right) >> 1;
        double leftMinDis = divide(left, mid, points);
        double rightMinDis = divide(mid, right, points);
        curMinDis = Math.min(leftMinDis, rightMinDis);
        List<Integer> validPointIndex = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.pow((points[i].x - points[mid].x), 2) <= curMinDis) {
                validPointIndex.add(i);
            }
        }
        for (int i = 0; i < validPointIndex.size() - 1; i++) {
            for (int j = i + 1; j < validPointIndex.size(); j++) {
                if (Math.pow((points[validPointIndex.get(i)].y - points[validPointIndex.get(j)].y), 2) > curMinDis) {
                    continue;
                }
                double tempDis = distance(points[validPointIndex.get(i)], points[validPointIndex.get(j)]);
                curMinDis = Math.min(tempDis, curMinDis);
            }
        }
        return curMinDis;

    }

    public static double distance(Point p1, Point p2) {
        return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
    }

}

/**
 * 定义点
 */
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

