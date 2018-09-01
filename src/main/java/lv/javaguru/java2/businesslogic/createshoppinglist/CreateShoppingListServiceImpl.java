package lv.javaguru.java2.businesslogic.createshoppinglist;

import lv.javaguru.java2.database.ShoppingListRepository;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.ShoppingList;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.ApplicationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class CreateShoppingListServiceImpl implements CreateShoppingListService {

    @Autowired private UserRepository userRepository;
    @Autowired private ShoppingListRepository shoppingListRepository;
    @Autowired private CreateShoppingListValidator validator;

    @Override
    @Transactional
    public CreateShoppingListResponse create(CreateShoppingListRequest request) {
        // find user
        Optional<User> userOpt = userRepository.findByLogin(request.getUserLogin());
        if (!userOpt.isPresent()) {
            return new CreateShoppingListResponse(buildErrorListWithUserNotFoundError());
        }
        User user = userOpt.get();

        // validate
        List<ApplicationError> validationErrors = validator.validate(user, request);
        if (!validationErrors.isEmpty()) {
            return new CreateShoppingListResponse(validationErrors);
        }

        // create new shoppingList
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setTitle(request.getTitle());

        // store to db
        shoppingListRepository.save(shoppingList);

        return new CreateShoppingListResponse(shoppingList.getId());
    }

    private List<ApplicationError> buildErrorListWithUserNotFoundError() {
        ApplicationError error = new ApplicationError("userLogin", "Not found");
        List<ApplicationError> errors = new ArrayList<>();
        errors.add(error);
        return errors;
    }

}
