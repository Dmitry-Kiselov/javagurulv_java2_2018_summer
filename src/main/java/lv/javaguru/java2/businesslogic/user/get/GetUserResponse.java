package lv.javaguru.java2.businesslogic.user.get;

import lv.javaguru.java2.web.dtos.UserDTO;

public class GetUserResponse {

    private UserDTO user;


    public GetUserResponse(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }
}
