package lv.javaguru.java2.domain.lamda;

import lv.javaguru.java2.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LamdaTest {

    public static void main(String[] args) {

        SearchCriteria author1 = new TitleSearchCriteria();

        SearchCriteria author2 = new SearchCriteria() {
            @Override
            public boolean match(Product p) {
                return p.getTitle().equals("Milk");
            }
        };

        SearchCriteria author3 = p -> p.getTitle().equals("Milk");
        SearchCriteria author4 = p -> p.getTitle().equals("Beer");

        List<Product> products = new ArrayList<>();
        search1(products, author1);
        search1(products, author3);
        search1(products, author4);

    }

    private static List<Product> search1(List<Product> products,
                                         SearchCriteria searchCriteria) {
        List<Product> found = new ArrayList<>();
        for (Product p : products) {
            if (searchCriteria.match(p)) {
                found.add(p);
            }
        }
        return found;
    }

    private static List<Product> search2(List<Product> products,
                                         SearchCriteria searchCriteria) {
        return products.stream()
                .filter(p -> searchCriteria.match(p))
                .collect(toList());
    }

}
