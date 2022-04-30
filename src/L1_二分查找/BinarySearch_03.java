package L1_二分查找;

import java.util.Arrays;

/**
 * 在一个有序数组中，找<=某个数最右侧的位置
 */
public class BinarySearch_03 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10000;
        compare(maxLength, maxRange, times);
    }

    /**
     * 对数器
     *
     * @param maxLength
     * @param maxRange
     * @param times
     */
    public static void compare(int maxLength, int maxRange, int times) {
        System.out.println("方法开始");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);
            int x = (int) (Math.random() * (maxRange + 1));

            if (methodA(arr, x) != methodB(arr, x)) {
                System.out.println("出错了！");
                System.out.println("数组" + Arrays.toString(arr) + "查找值" + x);
            }
        }

        System.out.println("方法结束");
    }

    /**
     * 生成随机有序数组
     *
     * @param maxLength
     * @param maxRange
     * @return
     */
    public static int[] generateRandomArray(int maxLength, int maxRange) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            int value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            arr[i] = value;
        }
        Arrays.sort(arr);
        return arr;
    }

    /**
     * 普通方法，遍历
     *
     * @param arr
     * @param x
     * @return
     */
    public static int methodA(int[] arr, int x) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= x) {
                index = i;
            }
        }
        return index;
    }

    /**
     * 二分法
     *
     * @param arr
     * @param x
     * @return
     */
    public static int methodB(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        int mid = 0;

        if (arr == null || arr.length == 0) {
            return -1;
        }

        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] <= x) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

}
