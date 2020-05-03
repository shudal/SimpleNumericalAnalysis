package moe.heing.chapterfour;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 改进欧拉法
public class PerfEuler {
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;
    public List<BigDecimal[]> cal(BigDecimal x0, BigDecimal y0, BigDecimal h, int N, MyFunction f) {
        List<BigDecimal[]> ans = new ArrayList<>();
        for (int i=1; i<=N; i++) {
            BigDecimal x1 = x0.add(h);

            BigDecimal b1 = f.get(x0, y0);
            b1 = b1.multiply(h);
            BigDecimal yp = y0.add(b1);

            BigDecimal b2 = f.get(x1, yp);
            b2 = b2.multiply(h);
            BigDecimal yc = y0.add(b2);

            BigDecimal y1 = yp.add(yc);
            y1 = y1.divide(new BigDecimal("2"), scale, roundingMode);

            BigDecimal[] bs = new BigDecimal[2];
            bs[0] = x1; bs[1] = y1;
            ans.add(bs);

            x0 = x1; y0 = y1;
        }
        return ans;
    }
    public static void main(String[] args) {
        MyFunctionOne myFunctionOne = new MyFunctionOne();
        RungeKuttaFourth rungeKuttaFourth = new RungeKuttaFourth();
        BigDecimal h = new BigDecimal("0.1");
        int N = 5;
        BigDecimal[] bs = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ONE};
        List<BigDecimal[]> ans = rungeKuttaFourth.cal(bs, h, N, myFunctionOne);
        System.out.println("okf");
    }
}
