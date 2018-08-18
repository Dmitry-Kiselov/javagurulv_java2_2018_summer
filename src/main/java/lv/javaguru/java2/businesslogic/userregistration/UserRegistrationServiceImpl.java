package lv.javaguru.java2.businesslogic.userregistration;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired private UserRegistrationValidator validator;
    @Autowired private UserRepository userRepository;

    @Override
    @Transactional
    public UserRegistrationResponse register(UserRegistrationRequest request) {

        // validate login and password
        List<Error> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserRegistrationResponse(validationErrors);
        }

        // create new user
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());

        // store to db
        userRepository.save(user);

        return new UserRegistrationResponse(user.getId());
    }

}
