package Loanable;

// Contract for account types that are allowed to request a loan.
public interface Loanable {
    // Returns true if the loan request is accepted.
    Boolean requestLoan(double amount);
}
