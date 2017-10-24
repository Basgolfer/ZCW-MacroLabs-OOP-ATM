import org.junit.Assert;
import org.junit.Test;


public class CLI_InterfaceTest {

    @Test
    public void welcomeMessageTest() {
        String expected = "Welcome to the ATM!\nAre you a new user?";
        String actual = CLI_Interface.welcomeMessage();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void printIntroMenuTest_InputNo() {
        String expected =
                        "Please select an option from the menu below:\n" +
                        "0: Withdraw\n" +
                        "1: Deposit\n" +
                        "2: Check account balance\n" +
                        "3: Create new account\n" +
                        "4: Delete existing account\n" +
                        "5: Logout";
        String actual = CLI_Interface.printIntroMenu("No");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void printIntroMenuTest_InputYes() {
        String expected = "Would you like to create an account?";
        String actual = CLI_Interface.printIntroMenu("Yes");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void printIntroMenuTest_InputOther() {
        String answer = "Other";
        String expected = "Please type 'Yes' or 'No' not: " + answer;
        String actual = CLI_Interface.printIntroMenu(answer);
        Assert.assertEquals(expected, actual);
    }
}
