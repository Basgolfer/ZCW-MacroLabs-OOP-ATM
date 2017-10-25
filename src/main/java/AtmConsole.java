import java.util.Scanner;

public class AtmConsole {

    public static String welcomeMessage() {
        String message = AtmConsole.getStringInput("Welcome to the ATM!\n" +
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
            String username = AtmConsole.getStringInput("Enter a user name.");
            String password = AtmConsole.getStringInput("Enter a user password.");
            AtmLogic.createAndAddUser(username, password);
            String message = "Your username is " + username
            + "\nYour password is " + password;
            System.out.println(message);
    }

    public static void getLoginDetails() {
        String username = AtmConsole.getStringInput("Enter your user name.");
        String password = AtmConsole.getStringInput("Enter your password.");
        AtmLogic.setCurrentUsername(username);
        AtmLogic.setCurrentPassword(password);
    }

    public static String loginFailure() {
        System.out.println("Username and password incorrect.");
        String input = AtmConsole.getStringInput("Would you like to try and login again?");
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
    String accountType = AtmConsole.getStringInput("Please enter an account type to create.");
    double balance = Double.parseDouble(AtmConsole.getStringInput("Please enter a starting balance for your account."));
    AtmLogic.addAccountToUser(accountType, balance);
    System.out.println("Account has been created. Re-directing to the main menu.\n");
    AtmConsole.printIntroMenu();
    }

    public static void exitMessage() {
        System.out.println("Thanks for your business\n");
    }

    protected static void checkBalance() {
        Account account = AtmConsole.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is " + String.format("$%,.2f\n", accountBalance));
    }

    protected static Account getAccountAttempt() {
        System.out.println(AtmLogic.getCurrentUser().getAccountsToString());
        int accountSelection = Integer.parseInt(AtmConsole.getStringInput("Please choose an account number."));
        Account account = AtmLogic.getCurrentUser().getSpecificAccount(accountSelection);
        if (account == null) {
            System.err.println("Please choose a valid account number.");
            account = AtmConsole.getAccountAttempt();
        }
        return account;
    }

    protected static void transferWithinAccounts() {
        if (AtmLogic.getCurrentUser().getNumberOfAccounts() <= 1) {
            System.out.println("Insufficient number of accounts.");
        }
        else {
            System.out.println("This is the account that you will be transferring from.");
            Account accountFrom = AtmConsole.getAccountAttempt();

            System.out.println("This is the account that you will be transferring into.");
            Account accountTo = AtmConsole.getAccountAttempt();

            while (accountFrom == accountTo) {
                System.err.println("\nYou can't transfer to the same account.");
                System.err.println("Please select a different transfer to account.");
                accountTo = AtmConsole.getAccountAttempt();
            }
            double transferAmount = Double.parseDouble(getStringInput("Enter an amount you would like to transfer."));

            transferAmount = validAmount(accountFrom, transferAmount, "transfer");

            accountFrom = AtmConsole.validWithdraw(accountFrom, transferAmount, "transfer");

            accountTo = AtmConsole.validDeposit(accountTo, transferAmount, "transfer");

            System.out.println("Transfer complete!\n");
            System.out.println("Your "  +accountFrom.getAccountType() + " balance is: " + String.format("$%,.2f", accountFrom.getBalance()));
            System.out.println("Your " + accountTo.getAccountType() + " balance is: " + String.format("$%,.2f\n", accountTo.getBalance()));
        }

    }

    public static double validAmount(Account account, double amount, String withdrawType) {
        double accountBalance = account.getBalance();
        while (amount <= 0 || amount > accountBalance) {
            if (amount <= 0) {
                System.err.println("Please enter an amount greater than 0.");
            }
            else if (amount > accountBalance) {
                System.err.println("Insufficient funds, please enter a lower amount.");
            }
            amount = Double.parseDouble(AtmConsole.getStringInput("Enter an amount you would like to " + withdrawType + "."));
        }
        return amount;
    }

    // did not have time to finish
//    protected static void closeAccount() {
//        Account account = AtmConsole.getAccountAttempt();
//        if (account.getBalance() > 0) {
//            String input = AtmConsole.getStringInput(
//            "In order to close the account you must transfer or withdraw all funds.\n" +
//             "1: Transfer\n"
//            + "2: Withdraw");
//
//        }
//    }

    public static Account validWithdraw(Account account, double amount, String withdrawType) {
        double accountBalance = account.getBalance();
        double returnAmount = validAmount(account, amount, withdrawType);
        account.setBalance(accountBalance - returnAmount);
        return account;
    }

    public static Account withdrawAttempt(String withdrawType) {
        Account account = AtmConsole.getAccountAttempt();
        double accountBalance = account.getBalance();
        System.out.println("Your current balance is " + String.format("$%,.2f", accountBalance));
        double withdrawAmount = Double.parseDouble(
                AtmConsole.getStringInput("Please choose an amount to " + withdrawType + "."));

        account = AtmConsole.validWithdraw(account, withdrawAmount, withdrawType);

        System.out.println("Your new balance is " + String.format("$%,.2f", account.getBalance()) + "\n");
        return account;
    }

    public static Account validDeposit(Account account, double amount, String withdrawType) {
        double accountBalance = account.getBalance();

        while (amount <= 0) {
            if (amount <= 0) {
                System.err.println("Please enter an amount greater than 0 to deposit.");
            }
            amount = Double.parseDouble(AtmConsole.getStringInput("Enter an amount you would like to deposit."));
        }
        account.setBalance(accountBalance + amount);
        return account;
    }

    public static Account depositAttempt(String depositType) {
        Account account = AtmConsole.getAccountAttempt();
        double newBalance = 0;
        System.out.println("Your current balance is " + String.format("$%,.2f\n", account.getBalance()));
        double depositAmount = Double.parseDouble(
                AtmConsole.getStringInput("Please choose an amount to " + depositType + "."));
        double accountBalance = account.getBalance();


        newBalance = accountBalance + depositAmount;
        account.setBalance(newBalance);
        System.out.println("Your new balance is " + String.format("$%,.2f", newBalance) + "\n");
        return account;
    }
}
