package lv.javaguru.java2.businesslogic.user.registration;

import lv.javaguru.java2.businesslogic.ApplicationError;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class UserRegistrationValidatorImpl implements UserRegistrationValidator {

    @Autowired private UserRepository userRepository;

    @Override
    public List<ApplicationError> validate(UserRegistrationRequest request) {
        List<ApplicationError> errors = new ArrayList<>();
        validateLogin(request.getLogin()).ifPresent(errors::add);
        validateDuplicateLogin(request.getLogin()).ifPresent(errors::add);
        validatePassword(request.getPassword()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ApplicationError> validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            return Optional.of(new ApplicationError("login", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ApplicationError> validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Optional.of(new ApplicationError("password", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ApplicationError> validateDuplicateLogin(String login) {
        if (login != null && !login.isEmpty()) {
            Optional<User> userOpt = userRepository.findByLogin(login);
            if (userOpt.isPresent()) {
                return Optional.of(new ApplicationError("login", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

}
