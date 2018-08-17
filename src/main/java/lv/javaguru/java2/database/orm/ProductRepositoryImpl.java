package lv.javaguru.java2.database.orm;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.domain.Product;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ProductRepositoryImpl extends ORMRepository
                                   implements ProductRepository {

    @Override
    public void addProduct(Product product) {
        session().save(product);
    }

    @Override
    public Optional<Product> getByTitle(String title) {
        Product product = (Product) session().createCriteria(Product.class)
                .add(Restrictions.eq("title", title))
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public boolean remove(Product product) {
        session().delete(product);
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return session()
                .createCriteria(Product.class)
                .list();
    }

}
