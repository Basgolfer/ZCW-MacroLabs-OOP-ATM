import java.util.ArrayList;

public class Account {
    private static int totalAccountNumber = 0;
    private int accountNumber;
    private double balance;
    private ArrayList<String> transactions;
    private String accountType;

    {
        totalAccountNumber++;
    }

    public Account(String accountType) {
        this(accountType, 0);
    }

    public Account (String accountType, double balance) {
        this(accountType, balance, totalAccountNumber);
    }

    private Account (String accountType, double balance, int accountNumber) {
        this.accountType = accountType;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return "Account: " +
                "accountNumber: " + accountNumber +
                ", balance: " + String.format("$%.2f", balance) +
                ", transactions: " + transactions +
                ", accountType: " + accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
