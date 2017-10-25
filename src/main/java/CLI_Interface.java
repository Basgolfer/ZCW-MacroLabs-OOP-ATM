import jdk.nashorn.internal.ir.WithNode;

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
        double newBalance = 0;
        System.out.println("Your current balance is " + String.format("$%,.2f", account.getBalance()));
        double withdrawAmount = Double.parseDouble(
                CLI_Interface.getStringInput("Please choose an amount to withdraw."));
        double accountBalance = account.getBalance();

            while (withdrawAmount <= 0 || withdrawAmount > accountBalance) {
                if (withdrawAmount <= 0) {
                    System.err.println("Please enter an amount greater than 0.");
                }
                else if (withdrawAmount > accountBalance) {
                    System.err.println("Insufficient Funds.");
                }
                else {
                    System.err.println("Error!");
                }
                withdrawAmount = Double.parseDouble(CLI_Interface.getStringInput("Enter an amount you would like to withdraw."));
            }
            newBalance = accountBalance - withdrawAmount;
            account.setBalance(newBalance);
        System.out.println("Your new balance is " + String.format("$%,.2f", newBalance) + "\n");
    }

    public static void depositAttempt() {
        Account account = CLI_Interface.getAccountAttempt();
        double newBalance = 0;
        System.out.println("Your current balance is " + String.format("$%,.2f\n", account.getBalance()));
        double depositAmount = Double.parseDouble(
                CLI_Interface.getStringInput("Please choose an amount to deposit."));
        double accountBalance = account.getBalance();

        while (depositAmount <= 0) {
            if (depositAmount <= 0) {
                System.err.println("Please enter an amount greater than 0 to deposit.");
            }
            else {
                System.err.println("Error!");
            }
            depositAmount = Double.parseDouble(CLI_Interface.getStringInput("Enter an amount you would like to deposit."));
        }
        newBalance = accountBalance + depositAmount;
        account.setBalance(newBalance);
        System.out.println("Your new balance is " + String.format("$%,.2f", newBalance) + "\n");
    }
}
