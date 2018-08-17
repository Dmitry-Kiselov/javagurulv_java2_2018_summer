package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductService {

    @Autowired private ProductValidator validator;
    @Autowired private ProductRepository repository;

    public AddProductResponse addProduct(String title,
                                         String description) {
        List<Error> validationErrors = validator.validate(title, description);
        if (!validationErrors.isEmpty()) {
            return new AddProductResponse(validationErrors);
        }

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);

        repository.addProduct(product);

        return new AddProductResponse(product.getId());
    }

}
