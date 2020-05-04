package moe.heing.funcs;

import moe.heing.chapterthree.Romberg;
import moe.heing.common.MyFunctionOneVar;
import moe.heing.common.funcs.MyFunctionSix;

import java.math.BigDecimal;

public class MyFuncA implements MyFunctionOneVar {
    @Override
    public BigDecimal get(BigDecimal x) {
        double xd = x.doubleValue();
        double x2 = 1 / xd;
        return BigDecimal.valueOf(x2);
    }

    public static void main(String[] args) {
        Romberg romberg = new Romberg();
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("5");
        BigDecimal delta = new BigDecimal("0.000000001");
        MyFuncA f = new MyFuncA();
        BigDecimal ans = romberg.cal(a,b,delta,f);
        System.out.println("ok");
    }
}
