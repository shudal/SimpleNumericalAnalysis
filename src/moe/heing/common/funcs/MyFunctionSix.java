package moe.heing.common.funcs;

import moe.heing.common.MyFunctionOneVar;

import java.math.BigDecimal;

public class MyFunctionSix implements MyFunctionOneVar {
    @Override
    public BigDecimal get(BigDecimal x) {
        double xd = x.doubleValue();

        if (Math.abs(xd) < 0.000000000000000000000000001) {
            return BigDecimal.ONE;
        }

        double d1 = Math.sin(xd);
        d1 /= xd;
        return new BigDecimal(d1);
    }
}
