package lv.javaguru.java2.web;

import lv.javaguru.java2.businesslogic.user.get.GetUserRequest;
import lv.javaguru.java2.businesslogic.user.get.GetUserResponse;
import lv.javaguru.java2.businesslogic.user.get.GetUserService;
import lv.javaguru.java2.businesslogic.user.registration.UserRegistrationRequest;
import lv.javaguru.java2.businesslogic.user.registration.UserRegistrationResponse;
import lv.javaguru.java2.businesslogic.user.registration.UserRegistrationService;
import lv.javaguru.java2.web.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private GetUserService getUserService;

    @ResponseBody
    @PostMapping(value = "/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserRegistrationRequest request = new UserRegistrationRequest(
                userDTO.getLogin(), userDTO.getPassword()
        );
        UserRegistrationResponse response = userRegistrationService.register(request);
        userDTO.setId(response.getUserId());
        return ResponseEntity.ok(userDTO);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable("id") Long userId) {
        GetUserRequest request = new GetUserRequest(userId);
        GetUserResponse response = getUserService.get(request);
        return response.getUser();
    }

}
