import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password;    }

    public int getNumberOfAccounts() { return accounts.size(); }

    protected void createAccount(String accountType, double balance) {
        Account account = new Account(accountType, balance);
        this.accounts.add(account);
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

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public String getAccountsToString() {
        String output = "";
        for (Account account : accounts) {
            output += "Account Number: " + account.getAccountNumber()
                    + ", Account Type: " + account.getAccountType()
                    + ", Account Balance: " + String.format("$%,.2f", account.getBalance()) + "\n";
        }
        return output;
    }
}
