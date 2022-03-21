package pers.algorithm.merge;

class MergeSort {
    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        // 处理边界
        if (arr == null || arr.length < 2)
            return;
        process(arr, 0, arr.length - 1);
    }

    // 把arr[l, r]排有序
    // T(N) = 2 * T(N / 2) + O(N)
    // O(N * logN)
    public static void process(int[] arr, int l, int r) {
        if (l == r)
            return;
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // 将左区间[l, mid] 与 右区间[mid + 1, r] 合并
    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p = l;
        int q = mid + 1;
        int idx = 0;
        while (p <= mid && q <= r)
            help[idx++] = arr[p] <= arr[q] ? arr[p++] : arr[q++];

        // 上面while循环结束，说明要么p越界，要么q越界，所以需要把剩余元素填完
        while (p <= mid)
            help[idx++] = arr[p++];

        while (q <= r) {
            help[idx++] = arr[q++];
        }

        System.arraycopy(help, 0, arr, l, r - l + 1);
    }

    // 迭代方法实现
    void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        int n = arr.length;
        int mergeSize = 1;  // 步长
        while (mergeSize < n) {  // O(logN)
            int l = 0;  // 当前左组的第一个位置
            while (l < n) {
                if (l + mergeSize >= n) // 左组不够数
                    break;
                int mid = l + mergeSize - 1;    // 当前左组的最后一个位置
                int r = mid + Math.min(mergeSize, n - mid - 1); // 当前右组的最后一个位置
                merge(arr, l, mid, r);      // O(N)
                l = r + 1;
            }
            if (mergeSize > n / 2)  // 防止mergeSize溢出导致死循环
                break;
            mergeSize <<= 1;
        }
    }
}