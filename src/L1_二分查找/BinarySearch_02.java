package L1_二分查找;

import java.util.Arrays;

/**
 * 在一个有序数组中，查找第一个大于等于该数值的值
 */
public class BinarySearch_02 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10000;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力解
     * 遍历查找数组 arr 中第一个大于等于该数值的值 x
     * 若查找到则返回该数值 x
     * 若未查询到则返回 -1
     */
    public static int methodA(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分法
     * 查找 [0,length-1] 中间值 mid 是否大于等于该查找数值 x
     * 若中间值 mid 大于等于该数值 x，则将范围调整为 [0,mid-1]，并使用索引 index 记录下该中间值 mid 的数值
     * 若中间值 mid 小于该数值 x，则将范围调整为 [mid+1,length-1]
     * 若范围越界，则数组查询查询完毕，此时索引 index 为第一个大于等于该数值 x 的数值
     * 若索引 index 未发生变化，则返回默认值 -1
     */
    public static int methodB(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        int index = -1;

        if (arr.length == 0) {
            return -1;
        }

        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] >= x) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
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
                System.out.println("出错了！");
                System.out.println("数组" + Arrays.toString(arr) + "查找值" + x);
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
