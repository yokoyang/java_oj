package Algorithm.division;

public class ReverseCount {
    private long cnt = 0;

    public static void main(String[] args) {

//        int[] c = {1, 3, 2, 3, 1};
        int[] c = {7, 5, 6, 4};
        System.out.println(findReverseCount(c));

    }

    public static int findReverseCount(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }
        int low = 0;
        int high = input.length - 1;
        return findReverseCountHelper(input, low, high);
    }

    public static int findReverseCountHelper(int[] input, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = (low + high) / 2;
        int left = findReverseCountHelper(input, low, mid);
        int right = findReverseCountHelper(input, mid + 1, high);
        int count = merge(input, mid, low, high);
        return left + right + count;

    }

//    private static int merge(int[] data, int start, int mid, int end) {
//        int[] tmpData = new int[end - start + 1];
//        int tmpIndex = 0;
//        int cnt = 0;
//        // 进行数据合并操作
//        int leftIndex = start;
//        int rightIndex = mid + 1;
//
//        while (leftIndex <= mid && rightIndex <= end) {
//            if (data[leftIndex] < data[rightIndex]) {
//                tmpData[tmpIndex++] = data[leftIndex++];
//            } else {
//                cnt += rightIndex - mid;
//                tmpData[tmpIndex++] = data[rightIndex++];
//            }
//        }
//
//        // 将未结束的数据拷贝至申请数据的尾部
//        int copyStartIndex;
//        int copyEndIndex;
//
//        if (rightIndex <= end) {
//            copyStartIndex = rightIndex;
//            copyEndIndex = end;
//        } else {
//            copyStartIndex = leftIndex;
//            copyEndIndex = mid;
//        }
//
//        while (copyStartIndex <= copyEndIndex) {
//            tmpData[tmpIndex++] = data[copyStartIndex++];
//        }
//
//        // 将数据重新规换回数组中
//        for (int i = 0; i <= end - start; i++) {
//            data[start + i] = tmpData[i];
//        }
//        return cnt;
//    }


    public static int merge(int[] input, int mid, int low, int high) {
        int[] temp = new int[input.length];
        for (int i = low; i <= high; i++) {
            temp[i] = input[i];
        }
        int leftEnd = mid;
        int rightEnd = high;
        int index = high;
        int count = 0;
        while (leftEnd >= low && rightEnd >= mid + 1) {
            if (temp[leftEnd] > temp[rightEnd]) {
                count = count + rightEnd - mid;
                input[index] = temp[leftEnd];
                leftEnd--;
            } else {
                input[index] = temp[rightEnd];
                rightEnd--;
            }
            index--;
        }
        while (leftEnd >= low) {
            input[index] = temp[leftEnd];
            leftEnd--;
            index--;
        }
        while (rightEnd >= mid + 1) {
            input[index] = temp[rightEnd];
            rightEnd--;
            index--;
        }
        return count;
    }

}
