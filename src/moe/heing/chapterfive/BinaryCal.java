package moe.heing.chapterfive;

import moe.heing.common.MyFunctionOneVar;
import moe.heing.common.MyFunctionOneVarOne;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 二分法求 非线性方程根
public class BinaryCal {
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;

    private static BigDecimal b2 = new BigDecimal("2");

    // f(a)f(b) < 0
    public BigDecimal[] cal(BigDecimal a, BigDecimal b, BigDecimal delta, MyFunctionOneVar f) {
        BigDecimal y0 = f.get(a);

        List<BigDecimal[]> table = new ArrayList<>();
        while (true) {
            BigDecimal x = a.add(b).divide(b2, scale, roundingMode);
            BigDecimal y = f.get(x);
            BigDecimal b3 = y.multiply(y0);

            BigDecimal[] bs2 = new BigDecimal[3];
            bs2[0] = a; bs2[1] = b; bs2[2] = x;
            table.add(bs2);

            if (b3.compareTo(BigDecimal.ZERO) > 0) {
                a = x;
            } else {
                b = x;
            }
            if (b.subtract(a).compareTo(delta) < 0) {
                BigDecimal[] bs = new BigDecimal[2];
                bs[0] = x; bs[1] = y;
                return bs;
            }
        }
    }
    public static void main(String[] args) {
        BinaryCal binaryCal = new BinaryCal();
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("1.5");
        MyFunctionOneVarOne myFunctionOneVarOne = new MyFunctionOneVarOne();
        BigDecimal[] bs = binaryCal.cal(a, b, new BigDecimal("0.005"), myFunctionOneVarOne);
        System.out.println("ok");
    }
}
