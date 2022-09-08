package L4_归并排序;

import java.util.Arrays;

/**
 * * 05 区间和的个数（给定一个数组arr，两整数lower、upper，arr中有多少个子数组的累加和在[lower,upper]范围上）
 */
public class MergeSort_05 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int lower = 1;
        int upper = 50;
        int times = 10;
        compare(maxLength, maxRange, lower, upper, times);
    }


    /**
     * 暴力法
     * 遍历数组，依次扩大数组范围，查询是否处于范围之间
     */
    public static int methodA(int[] arr, int lower, int upper) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum >= lower && sum <= upper) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 分治法
     * 使用前缀和数组 preSum 替换当前数组 arr，
     * 利用归并排序进行排序的同时检查当前有多少数的前缀和符合条件
     */
    public static int methodB(int[] arr, int lower, int upper) {
        int[] preSum = new int[arr.length];

        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }

        return process(preSum, lower, upper, 0, arr.length - 1);
    }


    public static int process(int[] arr, int lower, int upper, int left, int right) {
        if (left == right) {
            if (arr[left] >= lower && arr[left] <= upper) {
                return 1;
            }
            return 0;
        }

        int mid = left + (right - left) / 2;
        return process(arr, lower, upper, left, mid)
                + process(arr, lower, upper, mid + 1, right)
                + merge(arr, lower, upper, left, mid, right);
    }

    /**
     * 归并过程
     */
    public static int merge(int[] arr, int lower, int upper, int left, int mid, int right) {
        int ans = 0;

        int curL = left;
        int curR = left;

        // 遍历当前待排序的前缀数组右侧，调整左侧的游标长度，即每个前缀数当前有多少个符合条件的子数组
        for (int i = mid + 1; i <= right; i++) {
            int min = arr[i] - upper;
            int max = arr[i] - lower;

            while (curL <= mid && arr[curL] < min) {
                curL++;
            }

            while (curR <= mid && arr[curR] <= max) {
                curR++;
            }

            ans += curR - curL;
        }

        int p = left;
        int q = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;

        while (p <= mid && q <= right) {
            if (arr[p] < arr[q]) {
                help[index++] = arr[p++];
            } else {
                help[index++] = arr[q++];
            }
        }

        while (p <= mid) {
            help[index++] = arr[p++];
        }

        while (q <= right) {
            help[index++] = arr[q++];
        }

        System.arraycopy(help, 0, arr, left, help.length);

        return ans;

    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int lower, int upper, int times) {
        System.out.println("方法开始");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);
            int methodA = methodA(arr, lower, upper);
            int methodB = methodB(arr, lower, upper);
            if (methodA != methodB) {
                System.out.println("方法出错");
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("methodA = " + methodA);
                System.out.println("methodB = " + methodB);
            }
        }
        System.out.println("方法结束");
    }

    /**
     * 随机生成数组
     */
    public static int[] generateRandomArray(int maxLength, int maxRange) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] arr = new int[length];

        for (int i = 0; i < arr.length; i++) {
            int value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            arr[i] = value;
        }
        return arr;
    }

}
