public class UserFactory {

    public static void addNewUser(String username, String password) {
        User user = new User(username, password);
        UserWarehouse.addUser(user);
        CLI_Logic.setCurrentPassword(password);
        CLI_Logic.setCurrentUsername(username);
    }
}
