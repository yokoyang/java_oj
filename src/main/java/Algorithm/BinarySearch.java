package Algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BinarySearch {
    public int binarySearch(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else {
            return bsearchInternally(a, low, mid - 1, value);
        }
    }

    private static double sqrtByInc(int n, double precision) {
        int temp = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(precision)).intValue();
        boolean flag = false;
        int scale = 1;
        while (!flag) {
            if (Math.pow(10, scale) == temp) {
                flag = true;
            } else {
                scale++;
            }
        }

        int low = 0;
        int high = n;
        int mid = (low + high) / 2;
        // 二分查找找到平方根的区域
        while (low <= high) {
            if (mid * mid > n) {
                high = mid - 1;
            } else if (mid * mid < n) {
                if ((mid + 1) * (mid + 1) > n) {
                    break;
                }
                low = mid + 1;
            } else {
                break;
            }
            mid = (low + high) / 2;
        }
        // 按照精度的平方根做为步长
        double r = mid;
        while (Math.abs(n - r * r) > precision) {
            if (r * r < n) {
                r += precision;
                r = BigDecimal.valueOf(r).setScale(scale, RoundingMode.HALF_UP).doubleValue();
            } else {
                break;
            }
        }
        return r;
    }

    public int search_2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }

    public int bsearchFirstOne(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        long low = 1;
        long high = num;
        long mid = (low + high) / 2;
        while (true) {
            if (mid * mid > num) {
                high = mid - 1;
            } else if (mid * mid < num) {
                if ((mid + 1) * (mid + 1) > num) {
                    return false;
                }
                low = mid + 1;
            } else {
                return true;
            }
            mid = (low + high) / 2;
        }
    }

    private static int bsearchFirstBig(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) >> 1;
            if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || a[mid - 1] < value) {
                    return value;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int low = 0;
        int high = x;
        int mid = low + (high - low) / 2;
        while (low <= high) {
            int t = mid * mid;
            if (t == x) {
                return mid;
            }
            if (t > x) {
                high = mid - 1;
            } else if (t < x) {
                if ((mid + 1) * (mid + 1) > x) {
                    return mid;
                }
                low = mid + 1;
            }
            mid = low + (high - low) / 2;
        }
        return mid;
    }

    //    已知 sqrt (2)约等于 1.414，要求不用数学库，求 sqrt (2)精确到小数点后 10 位。
    public static double mySqrt3() {
        double precesion = 0.0000001;
        double l = 1.41;
        double r = 1.42;
        double mid = (l + r) / 2;
        while (r - l > precesion) {
            mid = (l + r) / 2;
            if (mid * mid < 2) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return mid;
    }

    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) >> 1;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int peakIndexInMountainArray(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (A[mid] > A[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
//        binarySearch.mySqrt(9);
//        System.out.println(binarySearch.isPerfectSquare(100));
//        System.out.println(sqrtByInc(2147395599, 0.0000000001));
        System.out.println(mySqrt3());
    }
}
