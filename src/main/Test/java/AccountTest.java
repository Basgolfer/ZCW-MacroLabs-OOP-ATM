import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    @Test
    public void AccountConstructorTest() {
        Account account = new Account("savings", 1000);
        String expected = "savings";
        double expectedBal = 1000.0;
        double actualBal = account.getBalance();
        String actual = account.getAccountType();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedBal, actualBal, .001);
    }

}
