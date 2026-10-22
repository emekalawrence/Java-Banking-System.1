package Bank1;

import Savings.CurrentAccount;
import Savings.SavingsAccount;

// Base class for all bank accounts.
// It defines common account details and the operations shared by savings and current accounts.
public abstract class BankAccount {
    // Unique identifier for each account.
    private int accountNumber;

    // Current available balance in the account.
    private double balance;

    private double LoanAmount = 0.0;

    // Name of the account holder.
    private String name;

    // Constructor used to initialize a new bank account.
    public BankAccount(int accountNumber, double balance, String name) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
    }

    // Updates the balance after deposits, withdrawals, or transfers.
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getTotalLoanAmount() {
        return LoanAmount;
    }

    public void addLoanAmount(double loanAmount) {
        this.LoanAmount += loanAmount;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    // Determines the type of account at runtime using instanceof.
    public String AccountType() {
        if (this instanceof SavingsAccount) {
            return "Savings Account";

        }
        if (this instanceof CurrentAccount) {
            return "Current Account";
        }
        return "Unknown";
    }

    // Subclasses must define how money is deposited.
    public abstract Boolean deposit(double amount);

    // Subclasses must define how money is withdrawn.
    public abstract Boolean withdraw(double amount);

    // Subclasses must calculate their own interest.
    public abstract double calculateInterest();

    // Every account type must be able to print its details.
    public abstract void display();
}
