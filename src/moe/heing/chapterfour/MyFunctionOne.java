package moe.heing.chapterfour;

import java.math.BigDecimal;

public class MyFunctionOne implements MyFunction{
    public int scale = 20;
    public int roundingMode = BigDecimal.ROUND_HALF_UP;

    @Override
    public BigDecimal get(BigDecimal x, BigDecimal y) {
        BigDecimal ans = x.multiply(y);

        return ans;
    }
}
