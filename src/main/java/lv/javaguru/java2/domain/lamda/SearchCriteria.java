package lv.javaguru.java2.domain.lamda;

import lv.javaguru.java2.domain.Product;

public interface SearchCriteria {

    boolean match(Product p);

}
