package moe.heing.chapterfive;

import moe.heing.common.funcs.MyFunctionThree;
import moe.heing.common.interfs.MyFunctionHavingOneVarAndFirstDerivative;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 肥牛迭代法
public class Newton {
    public boolean success;
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;

    // 1->奇异标志
    public int flag = 0;
    public BigDecimal cal(BigDecimal x0, BigDecimal delta, int N, MyFunctionHavingOneVarAndFirstDerivative f) {
        success = false;
        BigDecimal ans = BigDecimal.ZERO;
        List<BigDecimal> xTable = new ArrayList<>();
        for (int i=1; i<=N; i++) {
            BigDecimal b1 = f.getFirstDerivative(x0);
            if (b1.compareTo(BigDecimal.ZERO) == 0) {
                flag = 1;
                break;
            }
            BigDecimal b2 = f.get(x0);
            BigDecimal b3 = b2.divide(b1, scale, roundingMode);
            BigDecimal x1 = x0.subtract(b3);

            xTable.add(x0);
            if (x1.subtract(x0).abs().compareTo(delta) < 0) {
                xTable.add(x1);
                ans = x1;
                success = true;
                break;
            }
            x0 = x1;
        }
        return ans;
    }
    public static void main(String[] args) {
        MyFunctionThree f = new MyFunctionThree();
        BigDecimal x0 = new BigDecimal("0.5");
        Newton newton = new Newton();
        BigDecimal ans = newton.cal(x0, new BigDecimal("0.00000001"), 100, f);
        System.out.println(ans);
    }
}
