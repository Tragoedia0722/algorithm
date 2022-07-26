package L1_二分查找;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在一个无序数组中，值有可能正数、负数、或者零，数组中任由两个相邻的数一定不相等，请找到任意一个局部最小并返回。
 */
public class BinarySearch_04 {

    public static void main(String[] args) {
        int maxLength = 1000;
        int maxRange = 1000;
        int times = 10000;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历查找，查找出同时小于左右两位的局部最小值
     */
    public static List<Integer> methodA(int[] arr) {
        List<Integer> res = new ArrayList<>();
        // 若数组为空，返回 -1
        if (arr == null || arr.length == 0) {
            res.add(-1);
            return res;
        }
        // 若长度为1，返回自身
        if (arr.length == 1) {
            res.add(0);
            return res;
        }
        // 若首数字小于下一位，则首数字为局部最小值
        if (arr[0] < arr[1]) {
            res.add(0);
        }
        // 若尾数字小于次尾数，则尾数字为局部最小值
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            res.add(arr.length - 1);
        }
        // 正常情况若数字小于前后两位，则为局部最小值
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1] && arr[i] < arr[i - 1]) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 二分法
     * 查找 [0,length-1] 中间值 mid 是否大于右侧值 mid+1
     * 若中间值 mid 大于右侧值 mid+1，则局部最小值位于右侧范围 [mid+1,length-1]
     * 若中间值 mid 小于左侧值 mid-1，则局部最小值位于左侧范围 [0,mid-1]
     * 若中间值 mid 小于左侧值 mid-1 且小于右侧值 mid+1，则此时的中间值 mid 为局部最小值
     * 若范围越界则表明无局部最小值，返回 -1
     */
    public static int methodB(int[] arr) {
        // 若数组为空，返回 -1
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 若长度为1，返回自身
        if (arr.length == 1) {
            return 0;
        }
        // 若首数字小于下一位，则首数字为局部最小值
        if (arr[0] < arr[1]) {
            return 0;
        }
        // 若尾数字小于次尾数，则尾数字为局部最小值
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 2;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else return mid;
        }
        return -1;
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);

            List<Integer> integers = methodA(arr);
            int integer = methodB(arr);
            if (!integers.contains(integer)) {
                System.out.println("出错！");
                System.out.println(Arrays.toString(arr));
                System.out.println(methodA(arr));
                System.out.println(methodB(arr));
            }
        }
        System.out.println("通过！");

    }

    /**
     * 生成随机无序数组，且相邻不相等
     */
    public static int[] generateRandomArray(int maxLength, int maxRange) {
        int last = maxRange + 1; // 上一位数，初始为无法到达值
        int value;
        int length = (int) (Math.random() * (maxLength + 1));
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            do { // 确保前后两值不相等
                value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            } while (last == value);
            arr[i] = value;
            last = value;
        }
        return arr;
    }
}

