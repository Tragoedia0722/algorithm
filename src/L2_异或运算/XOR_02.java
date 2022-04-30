package L2_异或运算;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 */
public class XOR_02 {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxRange = 1000;
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
        int[] arr = generateRandomArray(maxLength, maxRange);
        for (int i = 0; i < times; i++) {
            if (methodA(arr) != methodB(arr)) {
                System.out.println("出错啦！");
                System.out.println(Arrays.toString(arr));
                System.out.println(methodA(arr));
                System.out.println(methodB(arr));
                return;
            }
        }
        System.out.println("通过！");
    }

    /**
     * 构造数组
     *
     * @param maxLength
     * @param maxRange
     * @return
     */
    public static int[] generateRandomArray(int maxLength, int maxRange) {
        int tempLength = (int) (Math.random() * (maxLength + 1));
        int length = tempLength % 2 == 0 ? tempLength + 1 : tempLength;
        int[] arr = new int[length];
        int current = 0;

        // 奇数项
        int temp = (int) (Math.random() * (10)); // [0,10)
        int odd = temp % 2 == 0 ? temp + 1 : temp; // 1,3,5,7,9
        int oddNum = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
        for (int i = 0; i < Math.min(odd, length); i++) {
            arr[i] = oddNum;
            current++;
        }
        int remain = Math.max((length - current), 0); // 剩余位数

        // 偶数项
        while (remain > 0) {
            temp = (int) (Math.random() * (10)); // [0,10)
            int tempEven = temp % 2 == 1 ? temp - 1 : temp; // 0,2,4,6,8
            int even = remain > tempEven ? tempEven : remain;
            remain = remain - even;

            int evenNum = 0;
            do {
                evenNum = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            } while (evenNum == oddNum);

            for (int i = 0; i < even; i++) {
                arr[current] = evenNum;
                current++;
            }
        }
        return arr;
    }

    /**
     * 哈希表
     *
     * @param arr
     * @return
     */
    public static int methodA(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            if (((int) entry.getValue()) % 2 != 0) {
                return ((int) entry.getKey());
            }
        }
        return -1;
    }

    /**
     * 亦或
     *
     * @param arr
     * @return
     */
    public static int methodB(int[] arr) {
        int a = arr[0];
        for (int i = 1; i < arr.length; i++) {
            a ^= arr[i];
        }
        return a;
    }
}
