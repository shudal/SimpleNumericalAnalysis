package moe.heing.common.funcs;

import moe.heing.common.interfs.MyFunctionHavingOneVarAndFirstDerivative;

import java.math.BigDecimal;

public class MyFunctionThree implements MyFunctionHavingOneVarAndFirstDerivative {
    @Override
    public BigDecimal get(BigDecimal x) {
        double xd = x.doubleValue();
        double x3 = Math.exp(xd);
        x3 *= xd;
        x3 -= 1;
        return new BigDecimal(x3);
    }

    @Override
    public BigDecimal getFirstDerivative(BigDecimal x) {
        double xd = x.doubleValue();

        double x3 = Math.exp(xd);
        x3 *= (1+xd);

        return new BigDecimal(x3);
    }
}
