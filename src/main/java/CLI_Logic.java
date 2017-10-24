public class CLI_Logic {
    private static String currentUsername;
    private static String currentPassword;

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
        String currentUser = CLI_Logic.getCurrentUsername();
        for (User user : UserWarehouse.getUsers()) {
            if (currentUser.equalsIgnoreCase(user.getUsername())) {
                user.createAccount(accountType, balance);
            }
        }
    }

    public static void menuSelection() {
        int selection = Integer.parseInt(CLI_Interface.getStringInput());
        if (selection == 7) {
            CLI_Logic.logout();
        }
    }

    public static void logout() {
        CLI_Interface.exitMessage();
        setCurrentUsername(null);
        setCurrentPassword(null);
        CLI_Logic.introLogic();
    }
}
