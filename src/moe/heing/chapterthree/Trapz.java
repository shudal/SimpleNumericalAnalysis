package moe.heing.chapterthree;

import moe.heing.common.MyFunctionOneVar;
import moe.heing.common.funcs.MyFunctionSix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 复化梯形法 自动控制误差
public class Trapz {
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;
    public BigDecimal cal(BigDecimal a, BigDecimal b, BigDecimal delta, MyFunctionOneVar f) {
        BigDecimal h = b.subtract(a);
        BigDecimal hDivide2 = h.divide(new BigDecimal("2"), scale, roundingMode);
        BigDecimal T1 = hDivide2.multiply(f.get(a).add(f.get(b)));
        BigDecimal ans = BigDecimal.ZERO;
        BigDecimal myBig2 = new BigDecimal("2");
        List<BigDecimal> tTable = new ArrayList<>();
        while (true) {
            hDivide2 = h.divide(new BigDecimal("2"), scale, roundingMode);
            BigDecimal s = BigDecimal.ZERO;
            BigDecimal x = a.add(hDivide2);
            while (x.compareTo(b) < 0) {
                s = s.add(f.get(x));
                x = x.add(h);
            }
            BigDecimal T1Divide2 = T1.divide(myBig2, scale, roundingMode);
            BigDecimal T2 = T1Divide2.add(hDivide2.multiply(s));
            tTable.add(T1);
            if (T2.subtract(T1).abs().compareTo(delta) < 0) {
                ans = T2;
                tTable.add(T2);
                break;
            } else {
                h = hDivide2;
                T1 = T2;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Trapz trapz = new Trapz();
        BigDecimal a = new BigDecimal("0");
        BigDecimal b = new BigDecimal("1");
        MyFunctionSix f = new MyFunctionSix();
        BigDecimal ans = trapz.cal(a, b, new BigDecimal("0.00000001"), f);
        System.out.println("ok");
    }
}
