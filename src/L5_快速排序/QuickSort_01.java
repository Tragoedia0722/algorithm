package L5_快速排序;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort_01 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10000;
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
    public static void methodB(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /**
     * 递归过程
     */
    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right); // 保证随机性
        int[] partition = partition(arr, left, right);
        process(arr, left, partition[0] - 1);
        process(arr, partition[1] + 1, right);
    }

    /**
     * 划分过程
     * 定义指针 less 为小于等于区，指向初始范围左侧前一位
     * 定义指针 more 为大于区，指向初始范围最右侧
     * 定义指针 index 为索引值，指向初始范围最左侧
     * 当索引未碰到大于区即 index<more 时，进行遍历判断
     * 当前索引指向数值等于数组最右侧值时，索引后移，其余不变
     * 当前索引指向数值小于数组最右侧值时，当前值与小于等于区后第一个数值交换，小于等于区后移，索引指针后移
     * 当前指针指向数值大于数组最右侧值时，大于等于区前移，当前值与大于区第一个数值交换，索引不变
     * 遍历结束后，交换最右侧基准数值和大于等于区指向指针，达到数值划分结果
     */
    public static int[] partition(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }

        if (left == right) {
            return new int[]{left, right};
        }

        int less = left - 1; // 小于等于区
        int more = right; // 大于区
        int index = left; // 索引

        while (index < more) {
            if (arr[index] == arr[right]) {
                index++;
            } else if (arr[index] < arr[right]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    /**
     * 数组内交换
     */
    public static void swap(int[] arr, int p, int q) {
        int temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        System.out.println("方法开始");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);
            int[] temp = arr;
            int[] arrA = methodA(arr);
            methodB(arr);

            if (!Arrays.equals(arrA, arr)) {
                System.out.println("arr = " + Arrays.toString(temp));
                System.out.println("arrA = " + Arrays.toString(arrA));
                System.out.println("arrB = " + Arrays.toString(arr));
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
