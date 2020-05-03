package moe.heing.common;

import java.math.BigDecimal;

public class MyFunctionOneVarOne implements MyFunctionOneVar{

    @Override
    public BigDecimal get(BigDecimal x) {
        BigDecimal b1 = x.multiply(x.multiply(x));
        b1 = b1.subtract(x);
        b1 = b1.subtract(BigDecimal.ONE);
        return b1;
    }
}
