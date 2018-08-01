package lv.javaguru.java2.servises;

import lv.javaguru.java2.database.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {

    @Mock private ProductValidator validator;
    @Mock private Database database;

    @InjectMocks
    private AddProductService service = new AddProductService();

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