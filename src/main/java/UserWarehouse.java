import java.util.ArrayList;

public class UserWarehouse {
    private static ArrayList<User> users = new ArrayList<User>();

    public static ArrayList<User> getUsers() { return users; }

    public static void addUser(User user) { UserWarehouse.users.add(user); }

    public static boolean doesUserExist(String username, String password) {

        boolean accountFound = false;

        for (User user : UserWarehouse.getUsers()) {
            if (username.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword())) {
                AtmLogic.setCurrentUser(user);
                //AtmConsole.printIntroMenu();
                accountFound = true;
            }
        }
        return accountFound;
    }
}


