package lv.javaguru.java2.businesslogic.createshoppinglist;

import lv.javaguru.java2.database.ShoppingListRepository;
import lv.javaguru.java2.domain.ShoppingList;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.ApplicationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class CreateShoppingListValidatorImpl implements CreateShoppingListValidator {

    @Autowired private ShoppingListRepository shoppingListRepository;

    @Override
    public List<ApplicationError> validate(User user,
                                           CreateShoppingListRequest request) {
        List<ApplicationError> errors = new ArrayList<>();
        validateTitle(request.getTitle()).ifPresent(errors::add);
        validateDuplicateTitle(user, request.getTitle()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ApplicationError> validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Optional.of(new ApplicationError("title", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ApplicationError> validateDuplicateTitle(User user, String title) {
        if (title != null && !title.isEmpty()) {
            Optional<ShoppingList> shoppingListOpt = shoppingListRepository.findByUserAndTitle(user, title);
            if (shoppingListOpt.isPresent()) {
                return Optional.of(new ApplicationError("title", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

}
