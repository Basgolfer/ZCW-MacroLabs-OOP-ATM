import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserWarehouseTest {

    ArrayList<User> userArrayList;
    User user;


    @Before
    public void setUp() {
        userArrayList = new ArrayList<User>();
        user = new User("Brian", "password");
    }

    @Test
    public void addGetUserTest() {
        User user1 = new User(user.getUsername(), user.getPassword());
        String expectedUsername = user1.getUsername();
        String expectedPassword = user1.getPassword();
        UserWarehouse.addUser(user1);
        User anotherUser = UserWarehouse.getUsers().get(0);
        String actualUsername = anotherUser.getUsername();
        String actualPassword = anotherUser.getPassword();
        Assert.assertEquals(expectedUsername, actualUsername);
        Assert.assertEquals(expectedPassword, actualPassword);
    }
}
