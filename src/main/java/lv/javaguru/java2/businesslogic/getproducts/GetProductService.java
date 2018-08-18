package lv.javaguru.java2.businesslogic.getproducts;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductService {

    private ProductRepository database;

    public GetProductService(ProductRepository database) {
        this.database = database;
    }

    public List<Product> getAllProducts() {
        return database.getAllProducts();
    }

}
