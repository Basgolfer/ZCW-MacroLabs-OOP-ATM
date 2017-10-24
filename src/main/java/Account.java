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
        this.accountType = accountType.toLowerCase();
        this.balance = 0.0;
        this.accountNumber = totalAccountNumber;
    }

    public Account (String accountType, double balance) {
        this(accountType);
        this.balance = balance;
    }

    public String getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return "Account: " +
                "accountNumber=" + accountNumber +
                ", balance=" + String.format("$%.2f", balance) +
                ", transactions=" + transactions +
                ", accountType='" + accountType + '\'';
    }

    public double getBalance() {
        return this.balance;
    }
}
