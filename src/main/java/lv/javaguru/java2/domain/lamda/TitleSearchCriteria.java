package lv.javaguru.java2.domain.lamda;

import lv.javaguru.java2.domain.Product;

public class TitleSearchCriteria implements SearchCriteria {
    @Override
    public boolean match(Product p) {
        return p.getTitle().equals("Milk");
    }
}
