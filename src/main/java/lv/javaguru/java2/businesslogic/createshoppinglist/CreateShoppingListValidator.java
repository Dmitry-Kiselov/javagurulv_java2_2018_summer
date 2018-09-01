package lv.javaguru.java2.businesslogic.createshoppinglist;

import lv.javaguru.java2.domain.User;

import lv.javaguru.java2.businesslogic.ApplicationError;

import java.util.List;

public interface CreateShoppingListValidator {

    List<ApplicationError> validate(User user, CreateShoppingListRequest request);

}
