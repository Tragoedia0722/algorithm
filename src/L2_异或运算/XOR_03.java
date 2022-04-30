package L2_异或运算;

/**
 * 怎么把一个二进制数，提取出最右侧的1出来
 */
public class XOR_03 {
    public static void main(String[] args) {
        int maxRange = 100000;
        int times = 1000;
        compare(maxRange, times);
    }

    /**
     * 对数器
     * @param maxRange
     * @param times
     */
    public static void compare(int maxRange, int times) {
        for (int i = 0; i < times; i++) {
            int value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            if (!methodA(value).equals(methodB(value))) {
                System.out.println("出错啦！");
                System.out.println(methodA(value));
                System.out.println(methodB(value));
                return;
            }
        }
        System.out.println("通过！");
    }


    /**
     * 遍历字符串
     *
     * @param a
     * @return
     */
    public static String methodA(int a) {
        String binaryString = Integer.toBinaryString(a);

        int index = -1;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            if (binaryString.charAt(i) == '1') {
                index = i;
                break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < binaryString.length() - index; i++) {
            if (i == 0) {
                stringBuilder.append(1);
            } else {
                stringBuilder.append(0);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 亦或
     *
     * @param a
     * @return
     */
    public static String methodB(int a) {
        a = a & (~a + 1);
        String binaryString = Integer.toBinaryString(a);

        return binaryString;
    }


}
