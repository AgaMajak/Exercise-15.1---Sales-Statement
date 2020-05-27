package logic;

import data.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SaleStatement {

    private static final String FILE_NAME = "SaleStatement.CSV";
    private static final File FILE = new File(FILE_NAME);

    private static BigDecimal sumNettoSale(List<Product> products) {
        BigDecimal nettoSale = BigDecimal.ZERO;
        for (Product product : products) {
            nettoSale = nettoSale.add((product.getPrice())).setScale(2, RoundingMode.HALF_UP);
        }
        return nettoSale;
    }

    private static BigDecimal sumVat(List<Product> products) {
        BigDecimal vatSum = BigDecimal.ZERO;
        for (Product product : products) {
            vatSum = vatSum.add(product.getPrice().multiply(product.getVat().divide(BigDecimal.valueOf(100)))).setScale(2, RoundingMode.HALF_UP);
        }
        return vatSum;
    }

    private static BigDecimal sumBruttoSale(List<Product> products) {
        BigDecimal bruttoSale = BigDecimal.ZERO;
        for (Product product : products) {
            bruttoSale = bruttoSale.add(product.getPrice().add(product.getPrice().multiply(product.getVat().divide(BigDecimal.valueOf(100))))).setScale(2, RoundingMode.HALF_UP);
        }
        return bruttoSale;
    }

    public static void showSalesStatement(List<Product> products) {
        System.out.println("Sprzedane produkty:");
        for (Product product : products) {
            System.out.println(product);
        }
        BigDecimal nettoSale = sumNettoSale(products);
        BigDecimal vatSum = sumVat(products);
        BigDecimal bruttoSale = sumBruttoSale(products);

        System.out.println("Sprzedaż netto: " + nettoSale + " zł");
        System.out.println("Suma podatku VAT ze sprzedaży: " + vatSum + " zł");
        System.out.println("Suma sprzedaży brutto: " + bruttoSale + " zł");
    }

    public static void writeMathOperationsResultInfo(List<Product> products) {
        FileCreator.createFile(FILE);
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            for (Product product : products) {
                String line = product.toString();
                writer.write(line);
                writer.newLine();
            }
            BigDecimal nettoSale = sumNettoSale(products);
            BigDecimal vatSum = sumVat(products);
            BigDecimal bruttoSale = sumBruttoSale(products);

            writer.write("Sprzedaż netto: " + nettoSale + " zł");
            writer.newLine();
            writer.write("Suma podatku VAT ze sprzedaży: " + vatSum + " zł");
            writer.newLine();
            writer.write("Suma sprzedaży brutto: " + bruttoSale + " zł");
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + FILE_NAME);
        }
    }
}