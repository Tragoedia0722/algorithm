package L6_堆排序;

import java.util.Arrays;

/**
 * 堆排序
 */
public class Heap_01 {
    public static void main(String[] args) {
        int range = 100;
        int length = 100;
        int times = 10;
        compare(length, range, times);
    }

    /**
     * 排序
     */
    public static void methodA(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 大根堆排序
     * 遍历 arr 依次进行 heapInsert 操作，构建初始大根堆
     * 记录当前需排序的数组 arr 的长度为 heapSize
     * 若当前 heapSize 值大于 0 则开始循环
     * 对数组头部数进行 heapify 操作，将最大值提到数组头部
     * 交换头部数至数组尾部 heapSize 处，将 heapSize 的数值-1
     */
    public static void methodB(int[] arr) {
        int heapSize = arr.length;

        // 构建初始大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        /*        swap(arr, 0, --heapSize);*/
        while (heapSize > 0) {
            heapify(arr, heapSize, 0);
            swap(arr, --heapSize, 0);
        }
    }

    /**
     * 插入堆（向上查询是否交换）
     * 若当前结点 index 值大于父结点 (index-1)/2 值
     * 则将当前节点与父节点交换，并更新原 index 的指针
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 堆化（向下查询是否交换）
     * 记录当前位置 index 的左孩子结点为 left
     * 若左孩子结点 left 小于堆容量 heapSize 则证明当前索引 index 存在左孩子结点，则开始执行循环
     * 获取当前 index 结点的左右孩子结点中较大值的结点并于当前 index 结点做比较
     * 若孩子结点中的较大值 bigger 大于当前 index 的值，则将 bigger 与 index 位置交换
     * 交换完毕后，更新 index 和 left 的位置
     */
    public static void heapify(int[] arr, int heapSize, int index) {
        int left = (2 * index + 1);
        while (left < heapSize) {
            // 左右子节点较大值
            int bigger = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 当前索引和子节点的较大值
            bigger = arr[bigger] > arr[index] ? bigger : index;
            if (bigger == index) {
                break;
            } else {
                swap(arr, bigger, index);
                index = bigger;
                left = (2 * index + 1);
            }
        }
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        System.out.println("方法开始");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxRange, maxLength);
            int[] arrA = Arrays.copyOf(arr, arr.length);
            int[] arrB = Arrays.copyOf(arr, arr.length);

            for (int j = 0; j < arr.length; j++) {
                if (arrA[j] != arrB[j]) {
                    System.out.println("方法出错！");
                    return;
                }
            }
        }
        System.out.println("方法结束");
    }

    /**
     * 数组内数字交换
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
