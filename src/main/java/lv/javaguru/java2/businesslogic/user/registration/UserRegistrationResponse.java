package lv.javaguru.java2.businesslogic.user.registration;

public class UserRegistrationResponse {

    private Long userId;

    public UserRegistrationResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
