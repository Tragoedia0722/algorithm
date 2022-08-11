package L3_链表_栈_队列;

import java.util.Arrays;

/**
 * 使用递归求数组中的最大值
 */
public class LinkedList_11 {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxRange = 100;
        int times = 1000;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历数组，记录当前最大值 max
     * 若当前数组值大于 max，则将当前值定为 max
     * 遍历完毕后返回 max 的值
     */
    public static int methodA(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    /**
     * 递归法
     * 递归出口：若当前范围只有 1 个值时返回
     * 递归体：左范围内与右范围内取最大值
     */
    public static int methodB(int[] arr, int left, int right) {
        // 当范围内只有一个数时，返回该数本身
        if (left == right) {
            return arr[left];
        }

        // 当范围内数值不止一个时，将范围分为左右子范围进行递归
        int mid = left + ((right - left) / 2);
        int leftMax = methodB(arr, left, mid);
        int rightMax = methodB(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

    /**
     * 随机生成数组 arr
     */
    public static int[] generateRandomArray(int maxLength, int maxRange) {
        int length = (int) (Math.random() * (maxLength)) + 1;
        int[] arr = new int[length];
        for (int i = 0; i < length - 1; i++) {
            arr[i] = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
        }
        return arr;
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        for (int i = 0; i < times; i++) {
            int[] ints = generateRandomArray(maxLength, maxRange);
            int i1 = methodA(ints);
            int i2 = methodB(ints, 0, ints.length - 1);
            if (i1 != i2) {
                System.out.println("未通过");
                System.out.println("ints = " + Arrays.toString(ints));
                System.out.println("i1 = " + i1);
                System.out.println("i2 = " + i2);
                break;
            }
        }
        System.out.println("通过");
    }
}
