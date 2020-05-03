package moe.heing.chapterfour;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 四阶Runge-Kutta方法
public class RungeKuttaFourth {
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;
    public boolean success;
    /*
     * 请调用者自觉确保参数合法
   * * startPoint长度为2，为(x0,y0)
     */
    public List<BigDecimal[]> cal(BigDecimal[] startPoint, BigDecimal h, int N, MyFunction f) {
        success = false;
        List<BigDecimal[]> ans = new ArrayList<>();

        BigDecimal x0 = startPoint[0], y0 = startPoint[1];
        BigDecimal halfH = h.divide(new BigDecimal("2"), scale, roundingMode);
        BigDecimal hDivide6 = h.divide(new BigDecimal("6"), scale, roundingMode);
        BigDecimal b2 = new BigDecimal("2");
        for (int i=1; i<=N; i++) {
            BigDecimal x1 = x0.add(h);
            BigDecimal k1 = f.get(x0, y0);
            BigDecimal k2 = f.get(x0.add(halfH), y0.add(halfH.multiply(k1)));
            BigDecimal k3 = f.get(x0.add(halfH), y0.add(halfH.multiply(k2)));
            BigDecimal k4 = f.get(x1, y0.add(h.multiply(k3)));
            BigDecimal y1 = y0.add(hDivide6.multiply(k1.add(k4.add(b2.multiply(k2.add(k3))))));

            BigDecimal[] bs = new BigDecimal[2];
            bs[0] = x1; bs[1] = y1;
            ans.add(bs);
            x0 = x1; y0 = y1;
        }

        success = true;
        return ans;
    }

    public static void main(String[] args) {
        MyFunctionOne myFunctionOne = new MyFunctionOne();
        RungeKuttaFourth rungeKuttaFourth = new RungeKuttaFourth();
        BigDecimal h = new BigDecimal("0.2");
        int N = 5;
        BigDecimal[] bs = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ONE};
        List<BigDecimal[]> ans = rungeKuttaFourth.cal(bs, h, N, myFunctionOne);
        System.out.println("okf");
    }
}
