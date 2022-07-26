package L1_二分查找;

import java.util.Arrays;

/**
 * 在一个有序数组中，找某个数是否存在
 */
public class BinarySearch_01 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10000;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力解
     * 遍历查找数值 x 是否存在于数组 arr 中
     * 若存在返回 true
     * 若不存在返回 false
     */
    public static boolean methodA(int[] arr, int x) {
        for (int i : arr) {
            if (i == x) {
                return true;
            }
        }
        return false;
    }

    /**
     * 二分法
     * 查找范围 [0,length-1] 中间值 mid 是否等于该查找数值 x
     * 若数值相等，则返回 true
     * 若中间值 mid 大于该数值 x，则范围修改为 [0,mid-1]
     * 若中间值 mid 小于该数值 x，则范围修改为 [mid+1,length-1]
     * 若范围越界，则表明该数值不在数组中，返回 false
     */
    public static boolean methodB(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1; // [0,length-1]
        int mid;
        if (arr.length == 0) {
            return false;
        }
        while (right >= left) { // 范围内至少两数时，余下一值单独判断
            mid = ((right - left) >> 1) + left;
            if (arr[mid] == x) {
                return true;
            } else if (arr[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        System.out.println("方法开始");

        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);
            int x = (int) (Math.random() * (maxRange + 1));
            if (methodA(arr, x) != methodB(arr, x)) {
                System.out.println("方法出错," + Arrays.toString(arr) + "查找数" + x);
            }
        }

        System.out.println("方法结束");
    }

    /**
     * 生成随机有序数组
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
}
