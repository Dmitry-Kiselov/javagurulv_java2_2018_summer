package lv.javaguru.java2.businesslogic.addproduct;

import lv.javaguru.java2.businesslogic.ApplicationError;

import java.util.List;

public interface AddProductValidator {

    List<ApplicationError> validate(String title,
                                    String description);

}
