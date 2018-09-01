package lv.javaguru.java2.businesslogic;

import java.util.ArrayList;
import java.util.List;

public class ApplicationException extends RuntimeException {

    private List<ApplicationError> errors;

    public ApplicationException(ApplicationError error) {
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public ApplicationException(List<ApplicationError> errors) {
        this.errors = errors;
    }

    public List<ApplicationError> getErrors() {
        return errors;
    }
}
