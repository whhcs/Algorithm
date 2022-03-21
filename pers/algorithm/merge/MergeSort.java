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
    public static void mergeSort2(int[] arr) {
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


    // 利用对数器进行测试
    // 生成随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // 随机生成长度为[0, maxSize]的数组
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        // 给数组的每个元素赋[-maxValue, maxValue]上的随机值。
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }

        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    // 判断两个数组是否相等
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
            return false;
        // 经过上面的if筛选后，只剩下两者都为null或都不会null的情况
        if (arr1 == null)
            return true;
        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}