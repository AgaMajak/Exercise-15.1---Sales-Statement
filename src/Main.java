import data.Product;
import logic.ProductFileReader;
import logic.SalesSummary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "ProductInfo.CSV";
        File file = new File(fileName);

        List<Product> products = ProductFileReader.returnListWithProductsFromFile(file);
        SalesSummary.showSalesStatement(products);
        SalesSummary.writeMathOperationsResultInfo(products);

    }
}
