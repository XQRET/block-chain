package cn.inbs.blockchain;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: Test.java
 * @Date: 2018/7/13 11:19
 * @author: create by zhangmingyang
 **/
public class Test {
    public static void main(String[] args) {
        int i = 10;
        Integer i1 = new Integer(10);
        StringBuffer name = new StringBuffer("zhang");
        ++i;
        ++i1;
        name.append("dan");
//        test(i, i1, name);

        System.err.println(i + "=====" + i1 + "=====" + name.toString());
    }

    private static void test(int i, Integer i1, StringBuffer name) {
        ++i;
        ++i1;
        name.append("dan");
    }
}
