package moe.heing.common.interfs;

import java.math.BigDecimal;

public interface MyFunctionHavingOneVarAndFirstDerivative {
    // f(x)
    public BigDecimal get(BigDecimal x);

    // 返回f(x)在x处的一阶导数值
    public BigDecimal getFirstDerivative(BigDecimal x);
}
