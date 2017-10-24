import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts;

    private void createAccount(String accountType) {
        Account account = new Account(accountType);
        accounts.add(account);
    }

    private void createAccount(String accountType, double balance) {
        Account account = new Account(accountType, balance);
        accounts.add(account);
    }


}
