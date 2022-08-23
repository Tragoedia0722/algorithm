package L4_归并排序;

import java.util.Arrays;

/**
 * 求一个数组中的数其右侧有多少数两倍后依然比当前数小的个数总和
 */
public class MergeSort_04 {
    public static void main(String[] args) {
        int maxLength = 100;
        int maxRange = 100;
        int times = 10;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历数组，查询当前数右侧数的值翻倍后是否比当前数小
     */
    public static int methodA(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] * 2 < arr[i]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 分治法
     * 定义游标指针 cursor 并指向右侧排序完毕的第一个数值 mid+1
     * 若游标未越界且当前游标指向的数 arr[cursor] 的两倍小于当前数 arr[i]的值，则将游标右移
     * 循环条件不满足时，此时游标 cursor 指向的是两倍后大于 arr[i] 的第一个数值，其左侧个数 (cursor-(mid+1)+1)-1 即为当前符合条件的数值
     * 重复此操作直至归并结束
     */
    public static int methodB(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;

        return methodB(arr, left, mid) +
                methodB(arr, mid + 1, right) +
                merge(arr, left, mid, right);
    }

    /**
     * 归并过程
     */
    public static int merge(int[] arr, int left, int mid, int right) {
        int ans = 0;
        int cursor = mid + 1;
        // 依次检查左侧有多少大于右侧两倍的数
        for (int i = left; i <= mid; i++) {
            while (cursor <= right && arr[i] > arr[cursor] * 2) {
                cursor++;
            }
            // 当前数左侧个数 (cursor - (mid + 1) + 1) - 1;
            ans += cursor - mid - 1;
        }

        int p = left;
        int q = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;

        while (p <= mid && q <= right) {
            if (arr[p] < arr[q]) {  // 相等时右侧指针先移动
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
            int[] arrB = Arrays.copyOf(arr, arr.length);
            int methodA = methodA(arr);
            int methodB = methodB(arrB, 0, arr.length - 1);
            if (methodA != methodB) {
                System.out.println("方法错误");
                System.out.println(Arrays.toString(arr));
                System.out.println("methodA = " + methodA);
                System.out.println("methodB = " + methodB);
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
