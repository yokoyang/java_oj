package Algorithm.division;

public class ReversePair {
    public int num;

    public void counterSort(int[] works, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        counterSort(works, start, mid);
        counterSort(works, mid + 1, end);
        merge(works, start, mid, end);
    }

    /**
     * 进行数据的合并操作
     *
     * <p>因为要构建逆序度，所以需要使用从大到小,先做排序操作，再做统计
     */
    private void merge(int[] works, int start, int mid, int end) {
        System.out.println("进入,start:" + start + ",mid:" + mid + ",end:" + end);
        int[] resultArray = new int[end - start + 1];
        int resultIndex = 0;
        int leftStart = start, rightstart = mid + 1;
        while (leftStart <= mid && rightstart <= end) {
            if (works[leftStart] > works[rightstart]) {
                num += (mid - leftStart + 1);
                resultArray[resultIndex] = works[rightstart];
                rightstart++;
                resultIndex++;
            } else {
                resultArray[resultIndex] = works[leftStart];
                leftStart++;
                resultIndex++;
            }
        }
        while (rightstart <= end) {
            resultArray[resultIndex] = works[rightstart];
            rightstart++;
            resultIndex++;
        }
        while (leftStart <= mid) {
            resultArray[resultIndex] = works[leftStart];
            leftStart++;
            resultIndex++;
        }
        // 将数据替换到原集合中
        for (int i = start; i <= end; i++) {
            works[i] = resultArray[i - start];
        }
    }

    public static void main(String[] args) {
        ReversePair rp = new ReversePair();
        int[] arrays = new int[]{1, 10, 6, 5, 2, 3, 4, 1};
        rp.counterSort(arrays, 0, arrays.length - 1);
    }
}
