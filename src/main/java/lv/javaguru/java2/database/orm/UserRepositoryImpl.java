package lv.javaguru.java2.database.orm;

import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.ShoppingList;
import lv.javaguru.java2.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class UserRepositoryImpl extends ORMRepository
        implements UserRepository {

    @Override
    public void save(User user) {
        session().save(user);
    }

    @Override
    public List<ShoppingList> findShoppingLists(User user) {
        String query = "from ShoppingList sl where sl.user = :user";
        return session().createQuery(query)
                .setParameter("user", user)
                .list();

        /*return session().createCriteria(ShoppingList.class)
                .add(Restrictions.eq("user", user))
                .list();*/
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = (User) session().createCriteria(User.class)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        User user = (User) session().createCriteria(User.class)
                .add(Restrictions.eq("id", userId))
                .uniqueResult();
        return Optional.ofNullable(user);
    }

}