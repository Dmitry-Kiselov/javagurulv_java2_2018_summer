package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ProductValidatorImplTest {

    private Database database;
    private ProductValidator validator;

    @Before
    public void init() {
        database = Mockito.mock(Database.class);
        validator = new ProductValidatorImpl(database);
    }

    @Test
    public void shouldReturnErrorWhenTitleIsNull() {
        List<Error> errors = validator.validate(null, "desc");

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getDescription(), "must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        List<Error> errors = validator.validate("", "desc");

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getDescription(), "must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsNull() {
        List<Error> errors = validator.validate("title", null);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "description");
        assertEquals(errors.get(0).getDescription(), "must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsEmpty() {
        List<Error> errors = validator.validate("title", "");

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "description");
        assertEquals(errors.get(0).getDescription(), "must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenProductAlreadyExistInDatabase() {
        Product existingFromDb = new Product();
        Mockito.when(database.getByTitle("title"))
                .thenReturn(Optional.of(existingFromDb));

        List<Error> errors = validator.validate("title", "dec");

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getDescription(), "duplicate not allowed");
    }

}