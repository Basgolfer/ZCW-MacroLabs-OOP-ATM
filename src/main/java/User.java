import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected void createAccount(String accountType) {
        Account account = new Account(accountType);
        accounts.add(account);
    }

    protected void createAccount(String accountType, double balance) {
        Account account = new Account(accountType, balance);
        accounts.add(account);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    protected ArrayList<Account> getAccounts() {
        return accounts;
    }
}
