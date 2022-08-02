package L2_异或运算;

import java.util.*;

/**
 * 一个数组中有一种数出现了K次，其他数都出现了M次（M大于1，K小于M），找到出现K次的数字
 */
public class XOR_05 {
    public static void main(String[] args) {
        int maxLength = 10000;
        int maxRange = 1000;
        int times = 1000;
        int k = 15;
        int m = 19;
        compare(maxLength, maxRange, times, k, m);

    }

    /**
     * 暴力法
     * 遍历数组 arr，将数字及出现次数存入哈希表 map 中
     * 遍历查找 map 中的 value 值，将 value 值为 k 的数值返回
     */
    public static int methodA(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // 获得出现 k 次的 value
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                return entry.getKey();
            }
        }

        return -1;
    }

    /**
     * 位运算法
     * 遍历数组 arr，将数组中元素转换为二进制格式并逐位追加到 32 位的二进制数组 binary 中，此时数组 binary 为相加总数的二进制格式
     * 按位将数组 binary 与 m 进行模操作，若结果为 0，则证明该位只有出现了 m 次的数字
     * 若当前位结果非 0，则此位是出现 k 次的数字的二进制数字 1，将该位或入到结果 ans 中
     * 返回出现了 k 次的结果 ans
     */
    public static int methodB(int[] arr, int k, int m) {
        int ans = 0;
        int[] binary = new int[32];

        for (int num : arr) {
            for (int i = 0; i < binary.length; i++) {
                if (((num >> i) & 1) != 0) {
                    binary[i]++;
                }
            }
        }

        for (int i = 0; i < binary.length; i++) {
            if (binary[i] % m != 0) {
                ans |= (1 << i);
            }
        }

        return ans;
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times, int k, int m) {
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLength, maxRange, k, m);
            int a = methodA(arr, k, m);
            int b = methodB(arr, k, m);
            if (a != b) {
                System.out.println("未通过");
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("a = " + a);
                System.out.println("b = " + b);
            }
        }
        System.out.println("通过");

    }

    /**
     * 生成数组
     */
    public static int[] generateRandomArray(int maxLength, int maxRange, int k, int m) {
        Set<Integer> nums = new HashSet<>(); // 数字集合防止重复
        List<Integer> list = new ArrayList<>();

        // 出现 k 次的数
        int kNum = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
        nums.add(kNum);
        for (int i = 0; i < k; i++) {
            list.add(kNum);
        }

        // 出现 m 次的数
        int left = maxLength - k;
        int mTimes = (left / m);
        for (int i = 0; i < mTimes; i++) {
            int mNum;
            do {
                mNum = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            } while (nums.contains(mNum));
            nums.add(kNum);
            for (int j = 0; j < m; j++) {
                list.add(mNum);
            }
        }

        return toIntArray(list);

    }

    /**
     * 列表转换为数字数组
     */
    static int[] toIntArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
