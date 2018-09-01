package lv.javaguru.java2.businesslogic.addproduct;

import lv.javaguru.java2.businesslogic.ApplicationError;

import java.util.List;

public class AddProductResponse {

    private boolean sussess;
    private Long productId;
    private List<ApplicationError> errors;

    public AddProductResponse(Long productId) {
        this.sussess = true;
        this.productId = productId;
    }

    public AddProductResponse(List<ApplicationError> errors) {
        this.sussess = false;
        this.errors = errors;
    }

    public boolean isSussess() {
        return sussess;
    }

    public Long getProductId() {
        return productId;
    }

    public List<ApplicationError> getErrors() {
        return errors;
    }
}
