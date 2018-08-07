package cn.inbs.blockchain.common.utils;

import java.util.*;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月16-10:12
 * @Author: createBy:zhangmingyang
 **/
public class BlockUtils {
    private static HashMap<String, String> initMap;

    static {
        initMap = new HashMap<String, String>();
        initMap.put("0", "6ae");
        initMap.put("1", "5Dgs");
        initMap.put("2", "hT");
        initMap.put("3", "Df");
        initMap.put("4", "P9Pv");
        initMap.put("5", "CC");
        initMap.put("6", "Gh");
        initMap.put("7", "ii");
        initMap.put("8", "r6t");
        initMap.put("9", "wS");
        initMap.put("a", "1t");
        initMap.put("b", "j");
        initMap.put("c", "5u");
        initMap.put("d", "d4");
        initMap.put("e", "0K");
        initMap.put("f", "LP");
        initMap.put("g", "Gy");
        initMap.put("h", "45");
        initMap.put("i", "0t");
        initMap.put("j", "Hf");
        initMap.put("k", "fd");
        initMap.put("l", "56");
        initMap.put("m", "yety");
        initMap.put("n", "54");
        initMap.put("o", "65");
        initMap.put("p", "Te");
        initMap.put("q", "6");
        initMap.put("r", "r");
        initMap.put("s", "y");
        initMap.put("t", "y");
        initMap.put("u", "");
        initMap.put("v", "865");
        initMap.put("w", "46");
        initMap.put("x", "7y");
        initMap.put("y", "BV");
        initMap.put("z", "Me");
        initMap.put("A", "fgd");
        initMap.put("B", "456");
        initMap.put("C", "w5");
        initMap.put("D", "52");
        initMap.put("E", "56");
        initMap.put("F", "G4");
        initMap.put("G", "Gr");
        initMap.put("H", "tw");
        initMap.put("I", "YE");
        initMap.put("J", "43");
        initMap.put("K", "ht");
        initMap.put("L", "hx");
        initMap.put("M", "6t");
        initMap.put("N", "7r");
        initMap.put("O", "w5");
        initMap.put("P", "5w");
        initMap.put("Q", "L4");
        initMap.put("R", "55");
        initMap.put("S", "t8");
        initMap.put("T", "56");
        initMap.put("U", "Gy");
        initMap.put("V", "Tr");
        initMap.put("W", "ts");
        initMap.put("X", "td");
        initMap.put("Y", "ffs");
        initMap.put("Z", "07d");
    }

    private static final int BLOCK_STR_LENTH = 40;
    private static final String SPACE_STR = "";

    /**
     * 创建区块ID
     *
     * @param val
     * @return
     */
    private static synchronized String createBlockChainStr(String val) {
        val = val + initMap.get(String.valueOf(val.length()));
        String[] split = val.split(SPACE_STR, -1);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            String append;
            if (null != initMap.get(s)) {
                append = initMap.get(s);
            } else {
                int hashRandom = split.length + Math.abs(val.hashCode());
                String[] hashRandomSplit = String.valueOf(hashRandom).split(SPACE_STR, -1);
                StringBuilder randomStringBuilder = new StringBuilder();
                for (String s1 : hashRandomSplit) {
                    if (null == initMap.get(s1)) {
                        randomStringBuilder.append(val.length());
                    } else {
                        randomStringBuilder.append(initMap.get(s1));
                    }
                }

                append = String.valueOf(randomStringBuilder.toString());
            }
            stringBuilder.append(append);
        }
        return upset(stringBuilder.toString()).substring(0, BLOCK_STR_LENTH);
    }

    /**
     * 按照一定规则打乱数据
     *
     * @param val
     * @return
     */
    private static String upset(String val) {
        String[] split = val.split(SPACE_STR, -1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            stringBuilder.append(split[split.length - 1 - i]);
        }

        return stringBuilder.toString();
    }

    /**
     * 无规则打乱
     *
     * @param val
     * @return
     */
    private static String upsetStr(String val) {
        List<String> list = Arrays.asList(val.split(SPACE_STR, -1));
        Collections.shuffle(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    /**
     * 创建公司区块ID
     *
     * @param mobile
     * @return
     */
    public static synchronized String createCompanyBlockId(String mobile) {
        return BlockUtils.createBlockChainStr(BlockUtils.upsetStr(System.currentTimeMillis() + mobile));
    }

    /**
     * 创建合约区块ID
     *
     * @return
     */
    public static synchronized String createContractBlockId(String houseCode) {
        return BlockUtils.createBlockChainStr(houseCode);
    }

    /**
     * 创建合约Id
     *
     * @param houseCode
     * @return
     */
    public static synchronized String createContractId(String houseCode) {
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 10; j++) {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        String numRandom = flag.toString();
        numRandom = (BlockUtils.createBlockChainStr(numRandom)).substring(0, 5);
        String substring = upsetStr(BlockUtils.createBlockChainStr(houseCode)).substring(0, 5);
        String random = numRandom + substring;
        return upsetStr(random);
    }
}
