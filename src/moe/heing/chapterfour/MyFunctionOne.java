package moe.heing.chapterfour;

import java.math.BigDecimal;

public class MyFunctionOne implements MyFunction{
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;

    @Override
    public BigDecimal get(BigDecimal x, BigDecimal y) {
        BigDecimal ans = BigDecimal.ZERO;


        BigDecimal b1 = x.multiply(new BigDecimal("2"));
        b1 = b1.divide(y, scale, roundingMode);
        ans = y.subtract(b1);

        return ans;
    }
}
