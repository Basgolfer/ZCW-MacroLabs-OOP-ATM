public class AtmLogic {
    private static String currentUsername;
    private static String currentPassword;
    private static User currentUser;

    public static void setCurrentUser(User currentUser) { AtmLogic.currentUser = currentUser;}
    public static User getCurrentUser() { return currentUser;}
    public static void setCurrentUsername(String currentUsername) { AtmLogic.currentUsername = currentUsername; }
    public static String getCurrentUsername() { return AtmLogic.currentUsername; }
    public static void setCurrentPassword(String currentPassword) { AtmLogic.currentPassword = currentPassword; }
    public static String getCurrentPassword() { return AtmLogic.currentPassword; }


    public static void introLogic() {
        String welcomeMessage = AtmConsole.welcomeMessage();
        if ("Yes".equalsIgnoreCase(welcomeMessage)) {
            AtmConsole.createNewUser();
            AtmConsole.createNewBankAccount();
            AtmLogic.menuSelection();
        }
        else if ("No".equalsIgnoreCase(welcomeMessage)) {
            AtmLogic.login();
            AtmLogic.menuSelection();
        }
        else {
            AtmConsole.invalidResponse(welcomeMessage);
            AtmLogic.introLogic();
        }
    }

    public static void createAndAddUser(String username, String password) {
        User user = new User(username, password);
        UserFactory.addNewUser(user);
    }

    private static void login() {
        AtmConsole.getLoginDetails();
        String currentUser = AtmLogic.getCurrentUsername();
        String currentPassword = AtmLogic.getCurrentPassword();
        boolean accountFound = UserWarehouse.doesUserExist(currentUser, currentPassword);
        if (accountFound) {
            AtmConsole.printIntroMenu();
        }
        else {
            String response = AtmConsole.loginFailure();
            if ("Yes".equalsIgnoreCase(response)) {
                AtmLogic.login();
            }
            else if ("No".equalsIgnoreCase(response)) {
                AtmLogic.introLogic();
            }
            else {
                AtmConsole.invalidResponse(response);
            }
        }
    }

    public static void addAccountToUser(String accountType, double balance) {
        currentUser.createAccount(accountType, balance);
    }

    public static void menuSelection() {
        int selection = Integer.parseInt(AtmConsole.getStringInput());
        switch (selection) {
            case 0:
                AtmConsole.withdrawAttempt("withdraw");
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 1:
                AtmConsole.depositAttempt("deposit");
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 2:
                AtmConsole.transferWithinAccounts();
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 3:
                AtmConsole.createNewBankAccount();
                AtmLogic.menuSelection();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                AtmConsole.checkBalance();
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
                break;
            case 7:
                AtmLogic.logout();
                break;
            default:
                AtmConsole.invalidResponse("" + selection + "\n");
                AtmConsole.printIntroMenu();
                AtmLogic.menuSelection();
        } // end Switch
    }

    public static void logout() {
        AtmConsole.exitMessage();
        setCurrentUsername(null);
        setCurrentPassword(null);
        setCurrentUser(null);
        AtmLogic.introLogic();
    }
}
