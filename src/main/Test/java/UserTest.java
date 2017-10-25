import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserTest {

    User newUser;
    String username;
    String password;
    String accountType;
    double balance;
    ArrayList<Account> accounts;

    @Before
        public void setUp() {
        newUser = new User("Brian", "password");
        username = newUser.getUsername();
        password = newUser.getPassword();
        accountType = "Savings";
        balance = 1000;
        accounts = new ArrayList<Account>();
    }


    @Test
    public void GetSetUsername() {
        String expected = newUser.getUsername();
        User actualUser = new User("Brian", "password");
        actualUser.setUsername("Brian");
        String actual = actualUser.getUsername();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSetPassword() {
        String expected = newUser.getPassword();
        User actualUser = new User("Brian", "password");
        actualUser.setPassword("password");
        String actual = actualUser.getPassword();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNumberOfAccountsTest() {
        int expected = 0;
        int actual = newUser.getNumberOfAccounts();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createAccountTest() {
        Account expectedAccount = new Account("Savings", 1000);
        newUser.createAccount("Savings", 1000);
        ArrayList<Account> actualAccounts = newUser.getAccounts();

        String expectedType = expectedAccount.getAccountType();
        double expectedBalance = expectedAccount.getBalance();

        String actualType =  actualAccounts.get(0).getAccountType();
        double actualBalance = actualAccounts.get(0).getBalance();

        Assert.assertEquals(expectedType, actualType);
        Assert.assertEquals(expectedBalance, actualBalance, .001);
    }

    @Test
    public void getAccountsTest() {
        Assert.assertNotNull(newUser.getAccounts());
    }

    @Test
    public void getSpecificAccountTest() {
        newUser.createAccount(accountType, balance);
        Account expected = newUser.getAccounts().get(0);
        Account actual = newUser.getSpecificAccount(0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountsToStringTest() {
        newUser.createAccount(accountType, balance);
        newUser.createAccount("Checkings", 500);
        newUser.createAccount("Investment", 10000);
        String expected = "Account Number: 0, Account Type: Savings, Account Balance: $1,000.00\n"
        + "Account Number: 1, Account Type: Checkings, Account Balance: $500.00\n"
        + "Account Number: 2, Account Type: Investment, Account Balance: $10,000.00\n";
        String actual = newUser.getAccountsToString();
        Assert.assertEquals(expected, actual);
    }
}
