package Savings;

import Bank1.BankAccount;
import Loanable.Loanable;

// A savings account keeps a minimum balance and earns interest.
public class SavingsAccount extends BankAccount implements Loanable {

    // The account cannot drop below this balance.
    private static final double MINIMUMBALANCE = 100;

    // Interest rate applied to the current balance.
    private static final double INTEREST_RATE = 0.05;

    // Maximum loan amount allowed for this savings account.
    private final double loanLimit = 10000.00;


    public SavingsAccount(int accountNumber, double balance, String name) {
        super(accountNumber, balance, name);
    }

    @Override
    public Boolean deposit(double amount) {
        // Only positive values are accepted as valid deposits.
        if (amount > 0) {
            setBalance(getBalance() + amount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean withdraw(double amount) {
        // Withdrawals are allowed only if the amount is valid and enough funds exist.
        if (amount > 0 && amount <= getBalance()) {

            // Ensure the balance does not fall below the minimum required balance.
            if (getBalance() - amount >= MINIMUMBALANCE) {
                setBalance(getBalance() - amount);
                System.out.println("Amount Withdraw Successfully ");
                System.out.println(" New Balance : M" + getBalance()+"\n");

                
                return true;
            } else {

                System.out.println("Savings must maintain 100");
                return false;

            }
        } else {
            System.out.println("Invalid Withdraw Amount ");
            return false;

        }
    }

    @Override
    public double calculateInterest() {
        // Simple interest calculation based on the current balance.
        return getBalance() * INTEREST_RATE;
    }

    @Override
    public void display() {
        System.out.println("Account Number : " + getAccountNumber());
        System.out.println("Account Name   : " + getName());
        System.out.println("Account Balance: M " + getBalance());
        System.out.println("Account Type : Savings");
    }

    @Override
    public Boolean requestLoan(double amount) {
        // Loan is allowed only within a valid positive range.

        if (getTotalLoanAmount()+amount <= loanLimit) {
            setBalance(getBalance() + amount);
            addLoanAmount(amount);
            return true;
        } else {
            System.out.println("Error: Loan request exceeds the maximum limit of M " + loanLimit + ".");
            return false;
        }
    }
   


}
