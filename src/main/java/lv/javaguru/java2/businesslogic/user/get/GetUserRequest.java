package lv.javaguru.java2.businesslogic.user.get;

public class GetUserRequest {

    private Long userId;

    public GetUserRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
