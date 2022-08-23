package L4_归并排序;

import java.util.Arrays;

/**
 * 求一个数组的小和（在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和）
 * 即查找一个数组中的数右侧有多少数比当前数值大
 */
public class MergeSort_02 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历数组，将数组当前数左侧比当前数值小的数相加
     */
    public static int methodA(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    ans += arr[j];
                }
            }
        }
        return ans;
    }

    /**
     * 归并法
     * 利用归并排序，每次进行归并时检查左侧指针 p 和右侧指针 q 两指针指向数值大小
     * 若左侧指针 p 所指向的数值大于右侧 q 所指向，则将右侧 q 指针偏移量（right-q+1）个数的 p 所指数值累加到结果 ans 中
     * 即计算出当前归并范围内另一数组中有多少数值大于当前 p 所指数值
     * 当 p q 所指数值相同时，优先移动右侧指针 q，便于计算后续数量
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
     */
    public static int merge(int[] arr, int left, int mid, int right) {
        int p = left;
        int q = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;
        int ans = 0;

        while (p <= mid && q <= right) {
            if (arr[p] < arr[q]) {
                ans += (right - q + 1) * arr[p];
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
