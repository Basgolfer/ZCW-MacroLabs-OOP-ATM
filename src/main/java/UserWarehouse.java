import java.util.ArrayList;

public class UserWarehouse {
    private static ArrayList<User> users = new ArrayList<User>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        UserWarehouse.users.add(user);
    }
}


