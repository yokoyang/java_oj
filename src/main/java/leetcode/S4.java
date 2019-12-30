package leetcode;

import java.util.*;

public class S4 {
//    There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//You may assume nums1 and nums2 cannot be both empty.
//
//Example 1:
//
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0

    public static void main(String[] args) {
        S4 s4 = new S4();
    }

    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length, n = B.length;
        if (m == 0 && n == 0) {
            return 0;
        }
        if (m > n) {
            return findMedianSortedArrays(B, A);
        }
        int half = (m + n + 1) / 2;
        boolean even = (m + n) % 2 == 0;
        int start = 0, end = m, aPart = 0, bPart = 0;
        while (start <= end) {
            aPart = (start + end) / 2;
            bPart = half - aPart;
            if (aPart > start && A[aPart - 1] > B[bPart]) {
                end = aPart - 1;
            } else if (aPart < end && A[aPart] < B[bPart]) {
                start = aPart + 1;
            } else {
                int leftMax = 0;
                if (aPart == 0) {
                    leftMax = B[bPart - 1];
                } else if (bPart == 0) {
                    leftMax = A[aPart - 1];
                } else {
                    leftMax = Math.max(A[aPart - 1], B[bPart - 1]);
                }
                if (!even) {
                    return leftMax;
                }
                int minRight = 0;
                if (aPart == m) {
                    minRight = B[bPart];
                } else if (bPart == n) {
                    minRight = A[aPart];
                } else {
                    minRight = Math.min(A[aPart], B[bPart]);
                }
                return (leftMax + minRight) / 2.0;
            }
        }
        return 0;
    }


}
