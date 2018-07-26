package lv.javaguru.java2.servises;

public class Error {

    private String field;
    private String description;

    public Error(String field, String description) {
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
