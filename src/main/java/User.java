import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<>();

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public Account getSpecificAccount(int index) {
        Account output = null;
        for (Account account : accounts) {
            if (account.getAccountNumber() == index) {
                output = account;
                return output;
            }
        }
        return output;
    }

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

    protected String getAccounts() {
        String output = "";
        for (Account account : accounts) {
            output += "Account Number: " + account.getAccountNumber()
                    + ", Account Type: " + account.getAccountType()
                    + ", Account Balance: " + String.format("$%,.2f", account.getBalance()) + "\n";
        }
        return output;
    }
}
