import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserFactoryTest {

    String expectedUsername;
    String expectedPassword;
    User user;

    @Before
    public void Setup () {
        expectedUsername = "Brian";
        expectedPassword = "1234";
        user = new User(expectedUsername, expectedPassword);
    }

    @Test
    public void addNewUserTest_UserTest() {
        User expected = user;
        UserFactory.addNewUser(user);
        ArrayList<User> actual = UserWarehouse.getUsers();
        String actualUsername = actual.get(0).getUsername();
        String actualPassword = actual.get(0).getPassword();
        Assert.assertEquals(expectedUsername, actualUsername);
        Assert.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void addNewUserTest_TwoInputs() {
        UserFactory.addNewUser(expectedUsername, expectedPassword);
        ArrayList<User> actual = UserWarehouse.getUsers();
        String actualUsername = actual.get(0).getUsername();
        String actualPassword = actual.get(0).getPassword();
        Assert.assertEquals(expectedUsername, actualUsername);
        Assert.assertEquals(expectedPassword, actualPassword);
    }


}
