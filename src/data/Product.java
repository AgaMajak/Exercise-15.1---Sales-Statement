package data;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private BigDecimal vat;

    public Product(String name, BigDecimal price, BigDecimal vat) {
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVat() {
        return vat;
    }

    @Override
    public String toString() {
        return String.format("%s, cena netto: %.2f zł, stawka vat: %.0f%%", name, price, vat);
    }
}
