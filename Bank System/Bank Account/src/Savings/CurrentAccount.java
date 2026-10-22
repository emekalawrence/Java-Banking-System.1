package Savings;

import Bank1.BankAccount;
import Loanable.Loanable;

// Current accounts can use a small overdraft and can also request a loan.
public class CurrentAccount extends BankAccount implements Loanable {
    // Maximum amount that can be withdrawn beyond the current balance.
    private static final double OVERDRAFT_LIMIT = 2000.00;

    // Maximum loan amount allowed for current accounts.
    private static final double LOAN_LIMIT = 20000.00;

    public CurrentAccount(int accountNumber, double balance, String name) {
        super(accountNumber, balance, name);
    }

    @Override
    public Boolean deposit(double amount) {
        // Deposits accept only positive values.
        if (amount > 0) {
            setBalance(getBalance() + amount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean withdraw(double amount) {
        // Withdrawals are allowed if the requested amount stays within available funds
        // plus overdraft.
        if (amount <= getBalance() + OVERDRAFT_LIMIT && amount > 0) {

            setBalance(getBalance() - amount);
            System.out.println("Amount Withdraw Successfully ");
            return true;
        } else {

            System.out.println(" Amount Must not Exceed Overdraft");
            return false;
        }
    }

    @Override
    public double calculateInterest() {
        // Current accounts in this project do not earn interest.
        
        return 0;
    }

    @Override
    public Boolean requestLoan(double amount) {
        // Loan is granted only for positive amounts under the limit.
        if (getTotalLoanAmount() + amount <= LOAN_LIMIT) {
            setBalance(getBalance() + amount);
            addLoanAmount(amount);
            return true;
        } else {
            System.out.println("Error: Loan request exceeds the maximum limit of M " + LOAN_LIMIT + ".");
            return false;
        }
    }

    @Override
    public void display() {
        System.out.println("Account Number  : " + getAccountNumber());
        System.out.println("Account Name    : " + getName());
        System.out.println("Account Balance : " + getBalance());
        System.out.println("Account Type  : Current Account ");
    }
}
