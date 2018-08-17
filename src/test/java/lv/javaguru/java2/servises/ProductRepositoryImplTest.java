package lv.javaguru.java2.servises;

import lv.javaguru.java2.config.SpringAppConfig;
import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringAppConfig.class })
public class ProductRepositoryImplTest {

    @Autowired
    private ProductRepository database;

/*
    @Before
    public void init() {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        this.database = context.getBean(ProductRepository.class);
    }
*/

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
