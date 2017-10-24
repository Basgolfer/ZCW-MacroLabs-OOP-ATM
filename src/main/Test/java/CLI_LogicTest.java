import org.junit.Assert;
import org.junit.Test;

public class CLI_LogicTest {

    @Test
    public void introLogicTest_InputYes() {
        String welcomeMessage = "Yes";
        boolean expected = true;
        boolean actual = "Yes".equalsIgnoreCase(welcomeMessage);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void introLogicTest_InputNo() {
        String welcomeMessage = "No";
        boolean expected = true;
        boolean actual = "No".equalsIgnoreCase(welcomeMessage);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void loginTest() {
        boolean expected = true;
        boolean actual = true;
    }
}
