import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AccountTest {

    Account account;
    int accountNumber;
    double balance;
    ArrayList<String> transactions;
    String accountType;

    @Before
    public void setUp() {
        accountType = "Savings";
        balance = 1000;
        account = new Account(accountType, balance);
        accountNumber = account.getAccountNumber();
    }

    @After
    public void cleanUp() {
        Account.setTotalAccountNumber(0);
    }

    @Test
    public void getAccountNumberTest() {
        int expected = 0;
        int actual = account.getAccountNumber();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountTypeTest() {
        String expected = "Savings";
        String actual = account.getAccountType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSetBalanceTest() {
        double expected = 52;
        account.setBalance(expected);
        double actual = account.getBalance();
        Assert.assertEquals(expected, actual, .001);
    }

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

    @Test
    public void getTransactionsTest() {
        ArrayList<String> expected = new ArrayList<String>();
        String withdraw = "Withdrew $100.00 from Savings: Account #0";
        String deposit = "Deposited $100.00 to Savings: Account #0";
        String transfer = "Transferred $100.00 from Savings: Account #0\n" +
                "into Checking: Account #1";
        expected.add(withdraw);
        expected.add(deposit);
        expected.add(transfer);
        ArrayList<String> actual = account.getTransactions();
        Assert.assertEquals(expected, actual);
    }


}
