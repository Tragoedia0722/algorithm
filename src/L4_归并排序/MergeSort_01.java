package L4_归并排序;

import java.util.Arrays;

/**
 * 归并排序的递归与非递归实现
 */
public class MergeSort_01 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10;

        compare(maxLength, maxRange, times);
    }

    /**
     * 递归法
     * 将当前数组的左右范围分别进行归并排序，若范围内只有一个数值时返回
     */
    public static void methodA(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        methodA(arr, left, mid);
        methodA(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    /**
     * 归并过程
     * 使用 p 和 q 两个指针分别指向 left 到 mid 和 mid+1 到 right 的两个有序数组片段
     * 若 p 指向数值小于等于 q 所指向数值，则将 p 所指向数值存入到辅助数组 help 中，并将 p 指针的下标右移
     * 否则若 q 指针指向数值小于 p 指针指向，则将 q 指向的数值存入数组 help 中，将 q 的指针下表右移
     * 待 p 指针和 q 指针有一方范围越界时，循环停止，将剩余的数值依次存入数组 help 中
     * 此时两有序片段重新排序完毕，辅助数组 help 即为重新排序结果
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        int p = left;
        int q = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;

        // 左右侧均在范围内时
        while (p <= mid && q <= right) {
            if (arr[p] <= arr[q]) {
                help[index++] = arr[p++];
            } else {
                help[index++] = arr[q++];
            }
        }

        // 左侧数值剩余时
        while (p <= mid) {
            help[index++] = arr[p++];
        }

        // 右侧数值剩余时
        while (q <= right) {
            help[index++] = arr[q++];
        }

        // 替换原数组
        System.arraycopy(help, 0, arr, left, help.length);
    }

    /**
     * 迭代法
     * 定义步长 step 为 1，当 step 小于数组长度 arr.length 时
     * 定义初始左侧指针 left 为 0，当左侧指针 left 所指小于数组长度 arr.length 时
     * 此时范围中值 mid 为左侧边界值加步长 left+step-1，范围右侧值 right 为中值加上步长 mid+step，为防止右侧值超过数组长度，限制 right 的最大值为数组长度减去中值 arr.length-mid-1
     * 对当前取值范围进行归并操作，完成后将左侧指针 left 指向下一组起始点 right+1
     * 针对当前步长 step 的聚合操作完成后，将步长 step 翻倍，实现递归效果
     */
    public static void methodB(int[] arr) {
        int step = 1;
        while (step < arr.length) {
            int left = 0;
            while (left < arr.length) {
                // 右侧范围为空时跳过
                if (step >= arr.length - left) {
                    break;
                }
                int mid = left + step - 1;
                int right = mid + Math.min(step, arr.length -1 - mid);
                merge(arr, left, mid, right);
                left = right + 1;
            }

            // 防越界溢出
            if (step > arr.length / 2) {
                break;
            }
            // 步长*2
            step = step * 2;
        }


    }

    /**
     * 暴力法
     * 双循环遍历，将当前数组最小值放置数组前端，遍历剩余数组
     */
    public static void methodC(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        System.out.println("方法开始");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange);
            int[] arrA = new int[arr.length];
            int[] arrB = new int[arr.length];
            int[] arrC = new int[arr.length];
            System.arraycopy(arr, 0, arrA, 0, arr.length);
            System.arraycopy(arr, 0, arrB, 0, arr.length);
            System.arraycopy(arr, 0, arrC, 0, arr.length);

            methodA(arrA, 0, arr.length - 1);
            methodB(arrB);
            methodC(arrC);

            for (int j = 0; j < arr.length; j++) {
                if (arrA[j] != arrC[j] || arrB[j] != arrC[j]) {
                    System.out.println("方法出错");
                    System.out.println("arrA = " + Arrays.toString(arrA));
                    System.out.println("arrB = " + Arrays.toString(arrB));
                    System.out.println("arrC = " + Arrays.toString(arrC));
                    break;
                }
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
        for (int i = 0; i < length; i++) {
            int value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            arr[i] = value;
        }
        return arr;
    }


}
