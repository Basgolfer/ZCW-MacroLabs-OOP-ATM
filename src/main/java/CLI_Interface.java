public class CLI_Interface{
    public static boolean isNewUser;

    public static String welcomeMessage() {
        String message = "Welcome to the ATM!\nAre you a new user?";
        System.out.println(message);
        return message;
    }

    public static String printIntroMenu(String answer) {
        String existingUserMenu =
                "Please select an option from the menu below:\n" +
                "0: Withdraw\n" +
                "1: Deposit\n" +
                "2: Check account balance\n" +
                "3: Create new account\n" +
                "4: Delete existing account\n" +
                "5: Logout";
        if ("No".equalsIgnoreCase(answer)) {
            System.out.println(existingUserMenu);
            return existingUserMenu;
        }
        else if ("Yes".equalsIgnoreCase(answer)) {
            String message = "Would you like to create an account?";
            System.out.println(message);
            return message;
        }
        else {
            String message = "Please type 'Yes' or 'No' not: " + answer;
            System.out.println(message);
            return message;
        }
    }

}
