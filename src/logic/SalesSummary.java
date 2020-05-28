package logic;

import data.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalesSummary {

    private static final String FILE_NAME = "SaleStatement.CSV";
    private static final File FILE = new File(FILE_NAME);

    public static void showSalesStatement(List<Product> products) {
        System.out.println("Sprzedane produkty:");
        for (Product product : products) {
            System.out.println(product);
        }
        StatementCalculator statementCalculator = StatementCalculator.calculateSalesStatement(products);

        System.out.println("Sprzedaż netto: " + statementCalculator.getNettoSale() + " zł");
        System.out.println("Suma podatku VAT ze sprzedaży: " + statementCalculator.getVatSum() + " zł");
        System.out.println("Suma sprzedaży brutto: " + statementCalculator.getBruttoSale() + " zł");
    }

    public static void writeSalesStatementToFile(List<Product> products) {
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
            StatementCalculator statementCalculator = StatementCalculator.calculateSalesStatement(products);

            writer.write("Sprzedaż netto: " + statementCalculator.getNettoSale() + " zł");
            writer.newLine();
            writer.write("Suma podatku VAT ze sprzedaży: " + statementCalculator.getVatSum() + " zł");
            writer.newLine();
            writer.write("Suma sprzedaży brutto: " + statementCalculator.getBruttoSale() + " zł");
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + FILE_NAME);
        }
    }
}