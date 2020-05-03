package moe.heing.chaptertwo;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 最小二乘法进行线性拟合
public class LeastSquaresMethod {
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;

    // 返回数组长度为2，[0]=>a,[1]=>b
    public boolean success = false;
    public BigDecimal[] cal(List<BigDecimal[]> pointArr) {
        success = false;
        BigDecimal[] ans = new BigDecimal[2];
        if (pointArr == null) return ans;
        int pointCount = pointArr.size();
        if (pointCount < 2) {
            return ans;
        }

        BigDecimal sumOfX = BigDecimal.ZERO;
        BigDecimal sumOfY = BigDecimal.ZERO;
        BigDecimal sumOfXSquare = BigDecimal.ZERO;
        BigDecimal sumOfXMulY = BigDecimal.ZERO;
        for (int i=0; i<pointCount; i++) {
            // 传入参数合法性检查
            BigDecimal[] bs = pointArr.get(i);
            if (bs == null || bs.length != 2) {
                return ans;
            }
            sumOfX = sumOfX.add(bs[0]);
            sumOfY = sumOfY.add(bs[1]);

            BigDecimal b1 = bs[0].multiply(bs[1]);
            sumOfXMulY = sumOfXMulY.add(b1);

            BigDecimal b2 = bs[0].multiply(bs[0]);
            sumOfXSquare = sumOfXSquare.add(b2);
        }

        BigDecimal N = new BigDecimal(pointCount);

        BigDecimal b1 = sumOfX.multiply(sumOfY);
        b1 = b1.divide(N, scale, roundingMode);
        BigDecimal b2 = sumOfXMulY.subtract(b1);

        BigDecimal b3_1 = sumOfX.multiply(sumOfX);
        BigDecimal b3_2 = b3_1.divide(N, scale, roundingMode);
        BigDecimal b3 = sumOfXSquare.subtract(b3_2);

        BigDecimal ans_b = b2.divide(b3,scale, roundingMode);

        BigDecimal b4 = ans_b.multiply(sumOfX);
        BigDecimal b5 = sumOfY.subtract(b4);
        BigDecimal ans_a = b5.divide(N, scale, roundingMode);

        ans[0] = ans_a;
        ans[1] = ans_b;
        return ans;
    }

    public static void main(String[] args) {
        Scanner sn = new Scanner(new BufferedInputStream(System.in));
        List<BigDecimal[]> pointArr = new ArrayList<>();
        int n = sn.nextInt();
        while ((n--) > 0) {
            String xS = sn.next();
            String yS = sn.next();
            BigDecimal[] bs = new BigDecimal[2];
            bs[0] = new BigDecimal(xS); bs[1] = new BigDecimal(yS);
            pointArr.add(bs);
        }
        LeastSquaresMethod leastSquaresMethod = new LeastSquaresMethod();
        BigDecimal[] ans = leastSquaresMethod.cal(pointArr);
        System.out.printf("a=%s\nb=%s\n",ans[0],ans[1]);
    }
}

/* 输入说明
5 <- n对点
165 x1
187 y1
123 x2
126 y2
150 x3
172 y3
123 x4
125 y4
141 x5
148 y5
    记得要有空行，不然上一行读不到

 */
/*
5
165
187
123
126
150
172
123
125
141
148

 */
/*
8
-3
-3.2
-2
-2.1
-1
-1.2
0
0.1
1
0.9
2
2.1
3
3.3
4
4


 */

