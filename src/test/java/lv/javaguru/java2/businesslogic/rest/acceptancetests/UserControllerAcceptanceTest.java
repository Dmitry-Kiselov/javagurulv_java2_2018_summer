package lv.javaguru.java2.businesslogic.rest.acceptancetests;

import lv.javaguru.java2.businesslogic.ApplicationError;
import lv.javaguru.java2.web.dtos.ApplicationExceptionDTO;
import lv.javaguru.java2.web.dtos.UserDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserControllerAcceptanceTest {

    private UserControllerActions actions = new UserControllerActions();

    @Test
    public void createUser() {
        String login = generateRandomString("login", 10000000);
        String password = generateRandomString("password", 10000000);
        UserDTO user = actions.createUser(login, password);
        assertTrue(user.getId() != null);
        assertTrue(user.getLogin().equals(login));
        assertTrue(user.getPassword().equals(password));
    }

    @Test
    public void getUser() {
        UserDTO newUser = actions.createRandomUser();
        UserDTO user = actions.getUser(newUser.getId());
        assertTrue(Objects.equals(user.getId(), newUser.getId()));
        assertTrue(Objects.equals(user.getLogin(), newUser.getLogin()));
        assertTrue(Objects.equals(user.getPassword(), newUser.getPassword()));
    }

    @Test
    public void createUserWithSameLogin() {
        UserDTO user = actions.createRandomUser();
        ResponseEntity<ApplicationExceptionDTO> responseWithException = actions.createUserWithException(user.getLogin(), user.getPassword());
        assertEquals(responseWithException.getStatusCode(), HttpStatus.FORBIDDEN);
        ApplicationExceptionDTO appEx = responseWithException.getBody();
        List<ApplicationError> errors = appEx.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getDescription(), "Must not be repeated");
    }

    @Test
    public void createUserWithEmptyLogin() {
        ResponseEntity<ApplicationExceptionDTO> responseWithException = actions.createUserWithException("", "password");
        assertEquals(responseWithException.getStatusCode(), HttpStatus.FORBIDDEN);
        ApplicationExceptionDTO appEx = responseWithException.getBody();
        List<ApplicationError> errors = appEx.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getDescription(), "Must not be empty");
    }

    @Test
    public void createUserWithEmptyPassword() {
        String login = generateRandomString("login", 10000000);
        ResponseEntity<ApplicationExceptionDTO> responseWithException = actions.createUserWithException(login, "");
        assertEquals(responseWithException.getStatusCode(), HttpStatus.FORBIDDEN);
        ApplicationExceptionDTO appEx = responseWithException.getBody();
        List<ApplicationError> errors = appEx.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getDescription(), "Must not be empty");
    }

    private String generateRandomString(String prefix, int bound) {
        Random random = new Random();
        return prefix + random.nextInt(bound);
    }

}
