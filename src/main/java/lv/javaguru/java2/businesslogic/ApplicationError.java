package lv.javaguru.java2.businesslogic;

public class ApplicationError {

    private String field;
    private String description;

    public ApplicationError() {
    }

    public ApplicationError(String field, String description) {
        this.field = field;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }
}
