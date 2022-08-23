package L4_归并排序;

import java.util.Arrays;

/**
 * 求解一个数组中逆序对个数（如果存在正整数 i, j 使得 1 ≤ i < j ≤ n 而且 A[i] > A[j]，则（A[i], A[j]） 这个有序对称为 A 的一个逆序对，也称作逆序数）
 * 即查找一个数组中的数右侧有多少数比当前数值小
 */
public class MergeSort_03 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历数组，查找当前数右侧有多少数值小于当前数
     */
    public static int methodA(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 分治法
     * 利用归并排序，每次进行归并时检查左侧指针 p 和右侧指针 q 两指针指向数值大小
     *
     */
    public static int methodB(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        return methodB(arr, left, mid) + methodB(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    /**
     * 归并过程
     * 指针从右向左移动（或逆序向右移动）
     */
    public static int merge(int[] arr, int left, int mid, int right) {
        int p = mid;
        int q = right;
        int[] help = new int[right - left + 1];
        int index = help.length - 1;
        int ans = 0;

        // 左右侧均在范围内时
        while (p >= left && q >= mid + 1) {
            if (arr[p] <= arr[q]) {
                help[index--] = arr[q--];
            } else {
                ans += q - (mid + 1) + 1;
                help[index--] = arr[p--];
            }
        }

        // 左侧数值剩余时
        while (p >= left) {
            help[index--] = arr[p--];
        }

        // 右侧数值剩余时
        while (q >= mid + 1) {
            help[index--] = arr[q--];
        }

        System.arraycopy(help, 0, arr, left, help.length);

        return ans;
    }


    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        System.out.println("方法开始");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);
            int[] arrA = Arrays.copyOf(arr, arr.length);
            int[] arrB = Arrays.copyOf(arr, arr.length);

            int i1 = methodA(arrA);
            int i2 = methodB(arrB, 0, arrB.length - 1);

            if (i1 != i2) {
                System.out.println("i1 = " + i1);
                System.out.println("i2 = " + i2);
                System.out.println("方法出错");
            }
        }
        System.out.println("方法结束");
    }

    /**
     * 生成随机数组
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
