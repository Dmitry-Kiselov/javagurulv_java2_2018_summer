package lv.javaguru.java2.businesslogic.user.registration;

import lv.javaguru.java2.businesslogic.ApplicationError;

import java.util.List;

public interface UserRegistrationValidator {

    List<ApplicationError> validate(UserRegistrationRequest request);

}
