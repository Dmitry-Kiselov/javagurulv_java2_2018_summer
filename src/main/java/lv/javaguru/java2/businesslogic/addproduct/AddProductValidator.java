package lv.javaguru.java2.businesslogic.addproduct;

import lv.javaguru.java2.businesslogic.Error;

import java.util.List;

public interface AddProductValidator {

    List<Error> validate(String title,
                         String description);

}
