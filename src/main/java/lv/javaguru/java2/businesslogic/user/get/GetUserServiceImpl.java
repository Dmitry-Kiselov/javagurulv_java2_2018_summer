package lv.javaguru.java2.businesslogic.user.get;

import lv.javaguru.java2.businesslogic.ApplicationError;
import lv.javaguru.java2.businesslogic.ApplicationException;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.web.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
class GetUserServiceImpl implements GetUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public GetUserResponse get(GetUserRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = new UserDTO(
                    user.getId(), user.getLogin(), user.getPassword()
            );
            return new GetUserResponse(userDTO);
        } else {
            ApplicationError error = new ApplicationError("id", "not found");
            throw new ApplicationException(error);
        }
    }

}
