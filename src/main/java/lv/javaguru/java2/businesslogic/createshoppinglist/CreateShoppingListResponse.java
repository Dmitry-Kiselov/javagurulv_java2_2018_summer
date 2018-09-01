package lv.javaguru.java2.businesslogic.createshoppinglist;

import lv.javaguru.java2.businesslogic.ApplicationError;
import java.util.List;

public class CreateShoppingListResponse {

    private Long shoppingListId;

    private boolean success;

    private List<ApplicationError> errors;

    public CreateShoppingListResponse(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
        this.success = true;
        this.errors = null;
    }

    public CreateShoppingListResponse(List<ApplicationError> errors) {
        this.shoppingListId = null;
        this.success = false;
        this.errors = errors;
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ApplicationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApplicationError> errors) {
        this.errors = errors;
    }

}
