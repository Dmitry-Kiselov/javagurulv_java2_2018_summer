package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintProductService {

    private ProductRepository database;

    public PrintProductService(ProductRepository database) {
        this.database = database;
    }

    public List<Product> getAllProducts() {
        return database.getAllProducts();
    }

}
