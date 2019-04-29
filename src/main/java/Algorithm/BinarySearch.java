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
            }
            else {
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


    public static void main(String[] args) {
        System.out.println(sqrtByInc(8, 0.001));
    }
}
