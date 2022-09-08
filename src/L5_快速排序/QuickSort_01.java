package L5_快速排序;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort_01 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10;
        compare(maxLength, maxRange, times);
    }

    /**
     * 普通排序
     */
    public static int[] methodA(int[] arr) {
        int[] ans = Arrays.copyOf(arr, arr.length);
        Arrays.sort(ans);
        return ans;
    }

    /**
     * 快速排序
     */
    public static int[] methodB(int[] arr, int left, int right) {
        // int[] ans = new int[right - left + 1];
        return arr;
    }

    public void partition() {

    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);
            int[] arrA = methodA(arr);
            int[] arrB = methodB(arr, 0, arr.length - 1);

            System.out.println("arrA = " + Arrays.toString(arrA));
            System.out.println("arrB = " + Arrays.toString(arrB));
        }

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
