package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintProductService {

    private Database database;

    public PrintProductService(Database database) {
        this.database = database;
    }

    public List<Product> getAllProducts() {
        return database.getAllProducts();
    }

}
