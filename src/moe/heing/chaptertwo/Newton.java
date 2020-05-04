package moe.heing.chaptertwo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
可通过debug查看差商表
代码中的差商表数组说明：n个点
0-> f(xi),长度为n
1-> 一阶差商们，长度为n，但下标0为null
2-> 二阶差商们，长度为n，但下标[0,1]为null
3-> 三阶差商们，长度为n，但下标[0,2]为null
 */
// 牛顿插值
public class Newton {
    public boolean success = true;
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;
    // 请自行确保kownarr中的数组 1不为null 2长度为2 3是合法
    public BigDecimal cal(BigDecimal x0, List<BigDecimal[]> knownarr) {
        success = false;
        BigDecimal ans = BigDecimal.ZERO;
        if (knownarr == null) {
            return ans;
        }
        int pointCount = knownarr.size();
        if (pointCount < 2) {
            return ans;
        }
        // n阶差商
        int n = pointCount - 1;

        BigDecimal[][] differenceQuotientTable = new BigDecimal[n+1][];

        // 计算0阶差商
        differenceQuotientTable[0] = new BigDecimal[n+1];
        for (int i=0; i<=n; i++) {
            differenceQuotientTable[0][i] = knownarr.get(i)[1];
        }
        for (int i=1; i<=n; i++) {
            // 计算每[1,n]阶差商

            // n+1行
            differenceQuotientTable[i] = new BigDecimal[n+1];
            BigDecimal[] differenceQuotients = differenceQuotientTable[i];

            // 第n阶差商从 i开始算起
            for (int k=i; k<=n; k++) {
                // 分子
                BigDecimal b1 = differenceQuotientTable[i-1][k];
                BigDecimal b2 = differenceQuotientTable[i-1][k-1];
                BigDecimal b3 = b1.subtract(b2);

                // 分母
                BigDecimal b4 = knownarr.get(k)[0];
                BigDecimal b5 = knownarr.get(k-i)[0];
                BigDecimal b6 = b4.subtract(b5);

                BigDecimal b7 = b3.divide(b6, scale, roundingMode);
                differenceQuotients[k] = b7;
            }
        }

        // 差商表计算完，开始计算值

        // n阶差商有 n+1 次计算
        for (int i=0; i<=n; i++) {
            BigDecimal subans = new BigDecimal("1");
            for (int k=0; k<i; k++) {
                BigDecimal b1 = knownarr.get(k)[0];
                BigDecimal b2 = x0;
                BigDecimal b3 = b2.subtract(b1);
                subans = subans.multiply(b3);
            }

            BigDecimal b1 = differenceQuotientTable[i][i];
            subans = subans.multiply(b1);

            ans = ans.add(subans);

        }

        success = true;
        return ans;
    }
    public static void main(String[] args) {
        List<BigDecimal[]> list = new ArrayList<>();
        /*
        BigDecimal x0 = new BigDecimal("-2"), y0 = new BigDecimal("-3");
        list.add(new BigDecimal[]{x0, y0});
        x0 = new BigDecimal("1"); y0 = new BigDecimal("0");
        list.add(new BigDecimal[]{x0, y0});
        x0 = new BigDecimal("3"); y0 = new BigDecimal("4");
        list.add(new BigDecimal[]{x0, y0});
        */

        BigDecimal x0, y0;
        x0 = new BigDecimal("1"); y0 = new BigDecimal("2");
        list.add(new BigDecimal[]{x0, y0});
        x0 = new BigDecimal("3"); y0 = new BigDecimal("6");
        list.add(new BigDecimal[]{x0, y0});
        x0 = new BigDecimal("4"); y0 = new BigDecimal("5");
        list.add(new BigDecimal[]{x0, y0});
        x0 = new BigDecimal("5"); y0 = new BigDecimal("4");
        list.add(new BigDecimal[]{x0, y0});

        Newton n = new Newton();
        BigDecimal ans = n.cal(new BigDecimal("2"), list);

        System.out.println("ans=" + ans);

        System.out.println("ok");
    }
}
