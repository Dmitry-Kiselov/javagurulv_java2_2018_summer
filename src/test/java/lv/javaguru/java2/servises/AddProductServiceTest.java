package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddProductServiceTest {

    private ProductValidator validator;
    private Database database;

    private AddProductService service;

    @Before
    public void init() {
        validator = Mockito.mock(ProductValidator.class);
        database = Mockito.mock(Database.class);
        service = new AddProductService(validator, database);
    }

    @Test
    public void shouldReturnFailedResponseWhenValidationErrorsExist() {
        List<Error> errors = Collections.singletonList(
                new Error("title", "must be not empty")
        );
        Mockito.when(validator.validate(null, "desc"))
                .thenReturn(errors);

        AddProductResponse response = service.addProduct(null, "desc");

        assertEquals(response.isSussess(), false);
    }

    @Test
    public void shouldReturnSuccessWhenValidationErrorsNotExist() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate("title", "desc"))
                .thenReturn(errors);

        AddProductResponse response = service.addProduct("title", "desc");

        assertEquals(response.isSussess(), true);
    }

}