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
            User user = new User(username, password);
            UserFactory.addNewUser(user);
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

    private static Account getAccountAttempt() {
        System.out.println(CLI_Logic.getCurrentUser().getAccounts());
        int accountSelection = Integer.parseInt(CLI_Interface.getStringInput("Please choose an account number."));
        Account account = CLI_Logic.getCurrentUser().getSpecificAccount(accountSelection);
        if (account == null) {
            System.err.println("Please choose a valid account number.");
            account = CLI_Interface.getAccountAttempt();
        }
        return account;
    }

    public static void withdrawAttempt() {
        Account account = CLI_Interface.getAccountAttempt();
        System.out.println("Your current balance is " + String.format("$%,.2f", account.getBalance()));
        double withdrawAmount = Double.parseDouble(
                CLI_Interface.getStringInput("Please choose an amount to withdraw."));
        double accountBalance = account.getBalance();

        double newBalance = CLI_Interface.withdrawError(withdrawAmount, accountBalance);

        account.setBalance(newBalance);

        System.out.println("Your new balance is " + String.format("$%,.2f", newBalance) + "\n");
    }

    private static double withdrawError(double withdrawAmount, double accountBalance) {
        if (withdrawAmount <= 0) {
            withdrawAmount = Double.parseDouble(CLI_Interface.getStringInput("Please enter an amount greater than 0."));
            withdrawAmount = withdrawError(withdrawAmount, accountBalance);
        }
        else if (withdrawAmount > accountBalance) {
            withdrawAmount = Double.parseDouble(CLI_Interface.getStringInput("Insufficient funds, please enter a lower amount."));
            withdrawAmount = withdrawError(withdrawAmount, accountBalance);
        }
        double newBalance = accountBalance - withdrawAmount;
        return newBalance;
    }
}
