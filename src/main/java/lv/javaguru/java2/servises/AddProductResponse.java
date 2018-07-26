package lv.javaguru.java2.servises;

import java.util.List;

public class AddProductResponse {

    private boolean sussess;
    private Long productId;
    private List<Error> errors;

    public AddProductResponse(Long productId) {
        this.sussess = true;
        this.productId = productId;
    }

    public AddProductResponse(List<Error> errors) {
        this.sussess = false;
        this.errors = errors;
    }

    public boolean isSussess() {
        return sussess;
    }

    public Long getProductId() {
        return productId;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
