package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;

import java.util.List;

public class AddProductService {

    private ProductValidator validator;
    private Database database;

    public AddProductService(ProductValidator validator,
                             Database database) {
        this.validator = validator;
        this.database = database;
    }

    public AddProductResponse addProduct(String title,
                                         String description) {
        List<Error> validationErrors = validator.validate(title, description);
        if (!validationErrors.isEmpty()) {
            return new AddProductResponse(validationErrors);
        }

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);

        database.addProduct(product);

        return new AddProductResponse(product.getId());
    }

}
