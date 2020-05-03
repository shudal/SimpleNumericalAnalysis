package moe.heing.chapterthree;

import moe.heing.common.MyFunctionOneVar;
import moe.heing.common.funcs.MyFunctionSix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Romberg算法求积分
public class Romberg {
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;
    BigDecimal myBig2 = new BigDecimal("2");
    BigDecimal myBig3 = new BigDecimal("3");
    BigDecimal myBig15 = new BigDecimal("15");
    BigDecimal myBig63 = new BigDecimal("63");


    public BigDecimal cal(BigDecimal a, BigDecimal b, BigDecimal delta, MyFunctionOneVar f) {
        BigDecimal h = b.subtract(a);
        BigDecimal T1 = getDivide2(h).multiply(f.get(a).add(f.get(b)));
        int k = 1;
        BigDecimal s1=BigDecimal.ZERO,c1=BigDecimal.ZERO,r1=BigDecimal.ZERO;
        BigDecimal ans = BigDecimal.ZERO;

        List<BigDecimal[]> table = new ArrayList<>();
        int tCount = 0;
        boolean ifBreak = false;
        while (true) {
            BigDecimal[] bs = new BigDecimal[5];
            bs[0] = BigDecimal.valueOf(tCount++);
            bs[1] = T1; bs[2]=s1; bs[3]=c1; bs[4]=r1;
            table.add(bs);
            if (ifBreak) {
                break;
            }

            BigDecimal s = BigDecimal.ZERO;
            BigDecimal x = a.add(getDivide2(h));
            while (x.compareTo(b) < 0) {
                s = s.add(f.get(x));
                x = x.add(h);
            }
            BigDecimal T2 = getDivide2(T1).add(s.multiply(getDivide2(h)));

            BigDecimal s2 = T2.add(getDivide3(T2.subtract(T1)));
            if (k == 1) {
                k++;
                h = getDivide2(h);
                T1 = T2;
                s1 = s2;
                continue;
            } else {
                BigDecimal c2 = s2.add(getDivide15(s2.subtract(s1)));
                if (k == 2) {
                    c1 = c2;

                    k++;
                    h = getDivide2(h);
                    T1 = T2;
                    s1 = s2;
                    continue;
                } else {
                    BigDecimal r2 = c2.add(getDivide63(c2.subtract(c1)));
                    if (k == 3) {
                        r1 = r2;
                        c1 = c2;

                        k++;
                        h = getDivide2(h);
                        T1 = T2;
                        s1 = s2;
                        continue;
                    } else {
                        if (r2.subtract(r1).abs().compareTo(delta) < 0) {
                            ans = r2;

                            r1 =r2;
                            ifBreak = true;
                            continue;

                        } else {
                            r1 = r2;
                            c1 = c2;

                            k++;
                            h = getDivide2(h);
                            T1 = T2;
                            s1 = s2;
                            continue;
                        }
                    }
                }
            }
        }
        return ans;
    }
    private BigDecimal getDivide2(BigDecimal b) {
        BigDecimal hDivide2 = b.divide(myBig2, scale, roundingMode);
        return hDivide2;
    }
    private BigDecimal getDivide3(BigDecimal b) {
        BigDecimal hDivide3 = b.divide(myBig3, scale, roundingMode);
        return hDivide3;
    }
    private BigDecimal getDivide15(BigDecimal b) {
        BigDecimal hDivide3 = b.divide(myBig15, scale, roundingMode);
        return hDivide3;
    }
    private BigDecimal getDivide63(BigDecimal b) {
        BigDecimal hDivide3 = b.divide(myBig63, scale, roundingMode);
        return hDivide3;
    }
    public static void main(String[] args) {
        Romberg romberg = new Romberg();
        BigDecimal a = new BigDecimal("0");
        BigDecimal b = new BigDecimal("1");
        BigDecimal delta = new BigDecimal("0.000000001");
        MyFunctionSix f = new MyFunctionSix();
        BigDecimal ans = romberg.cal(a,b,delta,f);
        System.out.println("ok");
    }
}
