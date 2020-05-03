package moe.heing.chapterfive;

import moe.heing.common.MyFunctionOneVar;
import moe.heing.common.funcs.MyFunctionFour;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

// 快速弦截法
public class QuickStringInterceptMethod {
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;
    public boolean success;
    public BigDecimal cal(BigDecimal x0, BigDecimal x1, BigDecimal delta, int N, MyFunctionOneVar f) {
        BigDecimal ans = BigDecimal.ZERO;
        List<BigDecimal> xTable = new ArrayList<>();
        for (int i=1; i<=N; i++) {
            BigDecimal fXk = f.get(x1);
            BigDecimal fXk_1 = f.get(x0);
            BigDecimal fenMu = fXk.subtract(fXk_1);
            BigDecimal b3 = fXk.divide(fenMu, scale, roundingMode);
            BigDecimal b1 = x1.subtract(x0);
            b3 = b3.multiply(b1);
            BigDecimal xKAdd1 = x1.subtract(b3);
            xTable.add(xKAdd1);
            if (xKAdd1.subtract(x1).abs().compareTo(delta) < 0) {
                success = true;
                ans = xKAdd1;
                break;
            }
            x0 = x1;
            x1 = xKAdd1;
        }

        return ans;
    }
    public static void main(String[] args) {
        MyFunctionFour f = new MyFunctionFour();
        QuickStringInterceptMethod quickStringInterceptMethod = new QuickStringInterceptMethod();
        BigDecimal x0 = new BigDecimal("0.5");
        BigDecimal x1 = new BigDecimal("0.6");
        BigDecimal ans = quickStringInterceptMethod.cal(x0, x1, new BigDecimal("0.000001"), 100, f);
        System.out.println("ok");
    }
}
