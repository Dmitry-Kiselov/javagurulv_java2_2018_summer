package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveProductService {

    private Database database;

    public RemoveProductService(Database database) {
        this.database = database;
    }

    public boolean removeProductByTitle(String title) {
        Optional<Product> foundProduct = database.getByTitle(title);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            boolean isRemoved = database.remove(product);
            return isRemoved;
        } else {
            return false;
        }
    }

}
