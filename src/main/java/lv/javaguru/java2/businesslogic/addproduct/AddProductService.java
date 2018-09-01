package lv.javaguru.java2.businesslogic.addproduct;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.businesslogic.ApplicationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AddProductService {

    @Autowired private AddProductValidator validator;
    @Autowired private ProductRepository repository;

    @Transactional
    public AddProductResponse addProduct(String title,
                                         String description) {
        List<ApplicationError> validationErrors = validator.validate(title, description);
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
