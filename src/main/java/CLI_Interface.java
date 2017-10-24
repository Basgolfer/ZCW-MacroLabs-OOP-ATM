import java.util.Scanner;

public class CLI_Interface{

    public static String welcomeMessage() {
        String message = CLI_Interface.getStringInput("Welcome to the ATM!\n" +
                "Are you a new user?");
        return message;
    }

    public static void printIntroMenu() {
        String existingUserMenu = "Please select an option from the menu below:\n" +
                "0: Withdraw\n" +
                "1: Deposit\n" +
                "2: Transfer\n" +
                "3: Create new account\n" +
                "4: Close existing account\n" +
                "5: Print transaction history\n" +
                "6: Check account balance\n" +
                "7: Logout";
        System.out.println(existingUserMenu);
    }

    public static void invalidResponse (String answer) {
        String message = "Please return a valid response not: " + answer;
        System.out.println(message);
    }

    public static void createNewUser() {
            String username = CLI_Interface.getStringInput("Enter a user name.");
            String password = CLI_Interface.getStringInput("Enter a user password.");
            UserFactory.addNewUser(username, password);
            String message = "Your username is " + username
            + "\nYour password is " + password;
            System.out.println(message);
    }

    public static void getLoginDetails() {
        String username = CLI_Interface.getStringInput("Enter your user name.");
        String password = CLI_Interface.getStringInput("Enter your password.");
        CLI_Logic.setCurrentUsername(username);
        CLI_Logic.setCurrentPassword(password);
    }

    public static String loginFailure() {
        System.out.println("Username and password incorrect.");
        String input = CLI_Interface.getStringInput("Would you like to try and login again?");
        return input;
    }

    public static String getStringInput(String input) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(input);
        return scanner.nextLine();
    }

    public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void createNewBankAccount() {
    String accountType = CLI_Interface.getStringInput("Please enter an account type to create.");
    double balance = Double.parseDouble(CLI_Interface.getStringInput("Please enter a starting balance for your account."));
    CLI_Logic.addAccountToUser(accountType, balance);
    System.out.println("Account has been created. Re-directing to the main menu.\n");
    CLI_Interface.printIntroMenu();
    }

    public static void exitMessage() {
        System.out.println("Thanks for your business\n");
    }

}
