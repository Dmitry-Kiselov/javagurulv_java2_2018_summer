package lv.javaguru.java2.businesslogic.addproduct;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.businesslogic.ApplicationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductValidatorImpl implements AddProductValidator {

    private ProductRepository database;

    public AddProductValidatorImpl(ProductRepository database) {
        this.database = database;
    }

    @Override
    public List<ApplicationError> validate(String title, String description) {
        List<ApplicationError> errors = new ArrayList<>();
        checkTitleNotBlankRule(title, errors);
        checkDescriptionNotBlank(description, errors);
        checkProductDuplicationRule(title, errors);
        return errors;
    }

    private void checkProductDuplicationRule(String title, List<ApplicationError> errors) {
        if (title != null && !title.isEmpty()) {
            Optional<Product> productOpt = database.getByTitle(title);
            if (productOpt.isPresent()) {
                ApplicationError error = new ApplicationError("title", "duplicate not allowed");
                errors.add(error);
            }
        }
    }

    private void checkDescriptionNotBlank(String description, List<ApplicationError> errors) {
        if (description == null || description.isEmpty()) {
            ApplicationError error = new ApplicationError("description", "must not be empty");
            errors.add(error);
        }
    }

    private void checkTitleNotBlankRule(String title,
                                        List<ApplicationError> errors) {
        if (title == null || title.isEmpty()) {
            ApplicationError error = new ApplicationError("title", "must not be empty");
            errors.add(error);
        }
    }

}
