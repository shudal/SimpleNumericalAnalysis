package moe.heing.chaptertwo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

// 拉格朗日插值
public class Lagrange {
    public boolean success = true;
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;
    // 传入参数的数组为(x,y)
    public BigDecimal cal(BigDecimal x0, List<BigDecimal[]> knownarr) {
        success = false;
        BigDecimal ans = BigDecimal.ZERO;
        if (knownarr == null) {
            return ans;
        }
        // 这里的n表示点数，而不是n阶Lagrange
        int n = knownarr.size();
        if (n < 2) {
            return ans;
        }
        for (int i=0; i<n; i++) {
            BigDecimal subans = BigDecimal.ONE;

            BigDecimal[] nowPair = knownarr.get(i);
            if (nowPair == null || nowPair.length != 2) {
                return ans;
            }

            subans = subans.multiply(nowPair[1]);
            for (int k=0; k<n; k++) {
                if (i == k) {
                    continue;
                }
                BigDecimal[] bs = knownarr.get(k);

                BigDecimal b1 = x0.subtract(bs[0]);
                BigDecimal b2 = nowPair[0].subtract(bs[0]);
                BigDecimal b3 = b1.divide(b2, scale, roundingMode);

                subans = subans.multiply(b3);
            }
            ans = ans.add(subans);
        }
        success = true;
        return ans;
    }

    public static void main(String[] args) {
        List<BigDecimal[]> list = new ArrayList<>();
        BigDecimal x0 = new BigDecimal("-2"), y0 = new BigDecimal("-3");
        list.add(new BigDecimal[]{x0, y0});
        x0 = new BigDecimal("1"); y0 = new BigDecimal("0");
        list.add(new BigDecimal[]{x0, y0});
        x0 = new BigDecimal("3"); y0 = new BigDecimal("4");
        list.add(new BigDecimal[]{x0, y0});


        Lagrange lagrange = new Lagrange();
        x0 = new BigDecimal("5");
        BigDecimal cal_ans = lagrange.cal(x0, list);

        BigDecimal ans = new BigDecimal(Math.sqrt(115));

        System.out.printf("success: %b\ncal_ans=%s\n", lagrange.success, cal_ans);
        System.out.println("    ans=" + ans);
        System.out.println("between=" + ans.subtract(cal_ans));
    }
}
