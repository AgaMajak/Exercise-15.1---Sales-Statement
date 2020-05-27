package logic;

import data.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductFileReader {

    public static List<Product> returnListWithProductsFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<Product> productList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] productElements = line.split(";");
            String name = productElements[0];
            BigDecimal price = new BigDecimal(productElements[1]);
            BigDecimal vat = new BigDecimal(productElements[2]);
            productList.add(new Product(name, price, vat));
        }
        scanner.close();
        return productList;
    }

}
