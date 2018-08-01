package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductValidatorImpl implements ProductValidator {

    private Database database;

    public ProductValidatorImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Error> validate(String title, String description) {
        List<Error> errors = new ArrayList<>();
        checkTitleNotBlankRule(title, errors);
        checkDescriptionNotBlank(description, errors);
        checkProductDuplicationRule(title, errors);
        return errors;
    }

    private void checkProductDuplicationRule(String title, List<Error> errors) {
        if (title != null && !title.isEmpty()) {
            Optional<Product> productOpt = database.getByTitle(title);
            if (productOpt.isPresent()) {
                Error error = new Error("title", "duplicate not allowed");
                errors.add(error);
            }
        }
    }

    private void checkDescriptionNotBlank(String description, List<Error> errors) {
        if (description == null || description.isEmpty()) {
            Error error = new Error("description", "must not be empty");
            errors.add(error);
        }
    }

    private void checkTitleNotBlankRule(String title,
                                        List<Error> errors) {
        if (title == null || title.isEmpty()) {
            Error error = new Error("title", "must not be empty");
            errors.add(error);
        }
    }

}
