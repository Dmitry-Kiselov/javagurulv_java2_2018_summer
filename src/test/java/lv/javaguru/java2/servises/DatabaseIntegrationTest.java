package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.JDBCDatabaseImpl;
import lv.javaguru.java2.domain.Product;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatabaseIntegrationTest {

    private Database database = new JDBCDatabaseImpl();

    @Test
    public void shouldAddProduct() {
        Product product = new Product();
        product.setTitle("p");
        product.setDescription("d");
        database.addProduct(product);
    }

    @Test
    public void getAllProducts() {
        List<Product> all1 = database.getAllProducts();

        Product product = new Product();
        product.setTitle("p");
        product.setDescription("d");
        database.addProduct(product);

        List<Product> all2 = database.getAllProducts();

        assertEquals(all2.size() - all1.size(), 1);
    }

}
