public class CLI_Logic {
    private static String currentUsername;
    private static String currentPassword;
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CLI_Logic.currentUser = currentUser;
    }

    public static String getCurrentUsername() {
        return CLI_Logic.currentUsername;
    }

    public static void setCurrentUsername(String currentUsername) {
        CLI_Logic.currentUsername = currentUsername;
    }

    public static String getCurrentPassword() {
        return CLI_Logic.currentPassword;
    }

    public static void setCurrentPassword(String currentPassword) {
        CLI_Logic.currentPassword = currentPassword;
    }

    public static void introLogic() {
        String welcomeMessage = CLI_Interface.welcomeMessage();
        if ("Yes".equalsIgnoreCase(welcomeMessage)) {
            CLI_Interface.createNewUser();
            CLI_Interface.createNewBankAccount();
            CLI_Logic.menuSelection();
        }
        else if ("No".equalsIgnoreCase(welcomeMessage)) {
            CLI_Logic.login();
            CLI_Logic.menuSelection();
        }
        else {
            CLI_Interface.invalidResponse(welcomeMessage);
            CLI_Logic.introLogic();
        }
    }

    private static void login() {
        CLI_Interface.getLoginDetails();
        String currentUser = CLI_Logic.getCurrentUsername();
        String currentPassword = CLI_Logic.getCurrentPassword();
        boolean accountFound = false;

        for (User user : UserWarehouse.getUsers()) {
            if (currentUser.equalsIgnoreCase(user.getUsername()) && currentPassword.equals(user.getPassword())) {
                setCurrentUser(user);
                CLI_Interface.printIntroMenu();
                accountFound = true;
            }
        }
        if (!accountFound) {
            String response = CLI_Interface.loginFailure();
            if ("Yes".equalsIgnoreCase(response)) {
                CLI_Logic.login();
            }
            else if ("No".equalsIgnoreCase(response)) {
                CLI_Logic.introLogic();
            }
            else {
                CLI_Interface.invalidResponse(response);
            }

        }
    }

    public static void addAccountToUser(String accountType, double balance) {
        currentUser.createAccount(accountType, balance);
    }

    public static void menuSelection() {
        int selection = Integer.parseInt(CLI_Interface.getStringInput());
        if (selection == 0) {
            //Withdraw
            CLI_Interface.withdrawAttempt();
            CLI_Interface.printIntroMenu();
            CLI_Logic.menuSelection();
        }
        else if (selection == 1) {
            CLI_Interface.depositAttempt();
            CLI_Interface.printIntroMenu();
            CLI_Logic.menuSelection();
        }
        else if (selection == 3) {
            //Create new account object.
            CLI_Interface.createNewBankAccount();
            CLI_Logic.menuSelection();
        }
        else if (selection == 7)  {
            CLI_Logic.logout();
        }
    }

    public static void logout() {
        CLI_Interface.exitMessage();
        setCurrentUsername(null);
        setCurrentPassword(null);
        setCurrentUser(null);
        CLI_Logic.introLogic();
    }
}
