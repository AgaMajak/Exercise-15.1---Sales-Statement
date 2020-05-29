package data;

import java.math.BigDecimal;
import java.util.List;

public class Statement {

    private BigDecimal bruttoSale;
    private BigDecimal vatSum;
    private BigDecimal nettoSale;

    public Statement(BigDecimal bruttoSale, BigDecimal vatSum, BigDecimal nettoSale) {
        this.bruttoSale = bruttoSale;
        this.vatSum = vatSum;
        this.nettoSale = nettoSale;
    }

    public BigDecimal getBruttoSale() {
        return bruttoSale;
    }

    public BigDecimal getVatSum() {
        return vatSum;
    }

    public BigDecimal getNettoSale() {
        return nettoSale;
    }

}
