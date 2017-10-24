import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void createAccountTest() {
        User user = new User("Andrew", "password");
        String expectedUsername = "Andrew";
        String expectedPassword = "password";
        String actualUsername = user.getUsername();
        String actualPassword = user.getPassword();
        Assert.assertEquals(expectedUsername, actualUsername);
        Assert.assertEquals(expectedPassword, actualPassword);
    }
}
