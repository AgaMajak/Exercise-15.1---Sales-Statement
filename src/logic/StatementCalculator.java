package logic;

import data.Product;
import data.Statement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StatementCalculator {


    public static Statement calculateSalesStatement(List<Product> products) {
        BigDecimal bruttoSale = BigDecimal.ZERO;
        BigDecimal vatSum = BigDecimal.ZERO;
        BigDecimal nettoSale = BigDecimal.ZERO;

        for (Product product : products) {
            bruttoSale = bruttoSale.add(product.getPrice().add(product.getPrice().multiply(product.getVat().divide(BigDecimal.valueOf(100))))).setScale(2, RoundingMode.HALF_UP);
            vatSum = vatSum.add(product.getPrice().multiply(product.getVat().divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP);
            nettoSale = nettoSale.add((product.getPrice())).setScale(2, RoundingMode.HALF_UP);
        }

        return new Statement(bruttoSale, vatSum, nettoSale);
    }

}