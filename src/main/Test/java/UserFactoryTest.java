import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UserFactoryTest {

    @Test
    public void addNewUserTest() {
        String expectedUsername = "Brian";
        String expectedPassword = "1234";
        User user = new User(expectedUsername, expectedPassword);
        UserFactory.addNewUser(expectedUsername, expectedPassword);
        ArrayList<User> actual = UserWarehouse.getUsers();
        String actualUsername = actual.get(0).getUsername();
        String actualPassword = actual.get(0).getPassword();
        Assert.assertEquals(expectedUsername, actualUsername);
        Assert.assertEquals(expectedPassword, actualPassword);
    }
}
