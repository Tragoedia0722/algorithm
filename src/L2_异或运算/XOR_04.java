package L2_异或运算;

import java.util.*;

/**
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class XOR_04 {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxRange = 1000;
        int times = 10000;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历数组 arr，将数字及出现个数存入哈希表 map 中
     * 遍历哈希表，将出现次数为奇数的值记录到列表 list 中
     * 返回 list
     */
    public static List<Integer> methodA(int[] arr) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() % 2 != 0) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    /**
     * 异或法
     * 遍历数组，将数组中所有数值异或，此时异或结果 xor 为两个出现奇数次数字的异或值
     * 取出异或结果最右侧位的 1，将其定义为 right
     * 遍历数组，将 right 与数组元素相与，由于存在两种不相同的奇数次的数字，故会根据相与结果是否为 0 分为两组
     * 取其中一组数进行异或，得到该数组中的一个出现奇数次的数值 a
     * 将数值 a 与异或结果 xor 异或，得到第二个出现奇数次的数值 b
     */
    public static List<Integer> methodB(int[] arr) {
        // 异或数组得到两个奇数次数字异或值
        int xor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xor ^= arr[i];
        }

        int right = xor & (~xor + 1); // 最右侧位的 1

        // 取其中一组数组进行异或，得到第一个出现奇数次的数值
        int a = 0;
        for (int i : arr) {
            if ((right & i) == 0) {
                a ^= i;
            }
        }

        int b = a ^ xor; // 将 a 与 xor 异或，得到第二个数字 b

        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        return list;
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        int[] arr = generateRandomArray(maxLength, maxRange);
        for (int i = 0; i < times; i++) {
            if (!new HashSet<>(methodA(arr)).containsAll(methodB(arr))) {
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
     * 生成具有两个奇数次，其余偶数次的的数组
     */
    public static int[] generateRandomArray(int maxLength, int maxRange) {
        int length = (int) (Math.random() * (maxLength + 1)) + 18;
        while (length % 2 != 0) {
            length += 1;
        }
        int[] arr = new int[length];
        int current = 0;

        Set<Integer> nums = new HashSet<>();

        // 奇数项
        for (int i = 0; i < 2; i++) {
            int value;
            do {
                value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
                nums.add(value);
            } while (!nums.contains(value));

            for (int j = 0, l = oddLength(); j < l; j++) {
                arr[current] = value;
                current += 1;
            }
        }

        // 偶数项
        while (current < arr.length) {
            int value;
            do {
                value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
                nums.add(value);
            } while (!nums.contains(value));

            for (int j = 0, l = evenLength(); j < l; j++) {
                if (current < arr.length) {
                    arr[current] = value;
                    current++;
                }
            }
        }
        return arr;
    }

    /**
     * 生成奇数项次的个数
     */
    public static int oddLength() {
        int[] oddLengths = new int[]{1, 3, 5, 7, 9};
        return oddLengths[(int) (Math.random() * (oddLengths.length))];
    }

    /**
     * 生成偶数项次的个数
     */
    public static int evenLength() {
        int[] evenLengths = new int[]{2, 4, 6, 8};
        return evenLengths[(int) (Math.random() * (evenLengths.length))];
    }
}
