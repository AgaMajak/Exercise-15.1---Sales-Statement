package logic;

import data.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StatementCalculator {

    private BigDecimal bruttoSale;
    private BigDecimal vatSum;
    private BigDecimal nettoSale;

    public StatementCalculator(BigDecimal bruttoSale, BigDecimal vatSum, BigDecimal nettoSale) {
        this.bruttoSale = bruttoSale;
        this.vatSum = vatSum;
        this.nettoSale = nettoSale;
    }

    public static StatementCalculator calculateSalesStatement(List<Product> products) {
        BigDecimal bruttoSale = BigDecimal.ZERO;
        BigDecimal vatSum = BigDecimal.ZERO;
        BigDecimal nettoSale = BigDecimal.ZERO;

        for (Product product : products) {
            bruttoSale = bruttoSale.add(product.getPrice().add(product.getPrice().multiply(product.getVat().divide(BigDecimal.valueOf(100))))).setScale(2, RoundingMode.HALF_UP);
            vatSum = vatSum.add(product.getPrice().multiply(product.getVat().divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP);
            nettoSale = nettoSale.add((product.getPrice())).setScale(2, RoundingMode.HALF_UP);
        }

        return new StatementCalculator(bruttoSale, vatSum, nettoSale);
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