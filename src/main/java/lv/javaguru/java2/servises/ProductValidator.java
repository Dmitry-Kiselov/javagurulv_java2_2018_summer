package lv.javaguru.java2.servises;

import java.util.List;

public interface ProductValidator {

    List<Error> validate(String title,
                         String description);

}
