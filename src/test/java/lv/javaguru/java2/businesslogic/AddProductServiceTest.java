package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductRepository;
import lv.javaguru.java2.businesslogic.addproduct.AddProductResponse;
import lv.javaguru.java2.businesslogic.addproduct.AddProductService;
import lv.javaguru.java2.businesslogic.addproduct.AddProductValidator;
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

    @Mock private AddProductValidator validator;
    @Mock private ProductRepository repository;

    @InjectMocks
    private AddProductService service = new AddProductService();

    @Test
    public void shouldReturnFailedResponseWhenValidationErrorsExist() {
        List<ApplicationError> errors = Collections.singletonList(
                new ApplicationError("title", "must be not empty")
        );
        Mockito.when(validator.validate(null, "desc"))
                .thenReturn(errors);

        AddProductResponse response = service.addProduct(null, "desc");

        assertEquals(response.isSussess(), false);
    }

    @Test
    public void shouldReturnSuccessWhenValidationErrorsNotExist() {
        List<ApplicationError> errors = new ArrayList<>();
        Mockito.when(validator.validate("title", "desc"))
                .thenReturn(errors);

        AddProductResponse response = service.addProduct("title", "desc");

        assertEquals(response.isSussess(), true);
    }

}