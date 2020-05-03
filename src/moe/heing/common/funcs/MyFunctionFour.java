package moe.heing.common.funcs;

import moe.heing.common.MyFunctionOneVar;

import java.math.BigDecimal;

public class MyFunctionFour implements MyFunctionOneVar {
    @Override
    public BigDecimal get(BigDecimal x) {
        double xd = x.doubleValue();
        double x3 = Math.exp(xd);
        x3 *= xd;
        x3 -= 1;
        return new BigDecimal(x3);
    }
}
