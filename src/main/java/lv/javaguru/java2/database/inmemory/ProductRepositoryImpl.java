package lv.javaguru.java2.database.inmemory;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> getByTitle(String title) {
        Product product = null;
        for (Product p : products) {
            if (p.getTitle().equals(title)) {
                product = p;
                break;
            }
        }
        return Optional.ofNullable(product);

/*
        return products.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
*/
    }

    @Override
    public boolean remove(Product product) {
        return products.remove(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

}
