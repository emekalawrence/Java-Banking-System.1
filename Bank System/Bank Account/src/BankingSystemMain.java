import Bank1.BankAccount;
import Loanable.Loanable;
import Savings.CurrentAccount;
import Savings.SavingsAccount;
import Transaction.Transactions;
import Transaction.LoadAccount;
import Transaction.SaveAccount;
import table2.table2;

import java.util.*;

// Entry point for the banking system application.
// This class manages the menu, account list, and all user transactions.
public class BankingSystemMain {
    // Keeps track of all bank accounts created during the program session.
  
    public static Set<Integer> accountsSet = new HashSet<Integer>();
    public static ArrayList<BankAccount> account = LoadAccount.loadAccounts(accountsSet);
    


    
    public static void displayOtherAvailableAccounts(int currentAccountNumber) {
        System.out.println("Available Accounts:");
        for (BankAccount bank : account) {
            if (bank.getAccountNumber() != currentAccountNumber) {
                System.out.println("Account Number: " + bank.getAccountNumber() + ", Account Holder: " + bank.getName());
            }
        }
    }


    public static int  accountNumber(){
        Scanner input = new Scanner(System.in);
        int accountNumber = input.nextInt();
        while (true) {

            while (true) {
                if (accountsSet.contains(accountNumber)) {
                    System.out.println("Account number already exists. Please enter a unique 9-digit account number.");
                    accountNumber = input.nextInt();
                } else {
                    accountsSet.add(accountNumber);
                    break; // Exit the inner loop if the account number is unique
                }
            }

            if (accountNumber < 100000000 || accountNumber > 999999999) {
            System.out.println("Invalid account number. Please enter a 9-digit number.");
            accountNumber = input.nextInt();
            } else {
                break; // Exit the loop if the account number is valid
            }
        
            // Recursively call the method until a valid account number is entered
            
        }
      
        return accountNumber;   
    }
        

    public static void timeDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean CheckAccountEmpty(String error) {
        while (true) {
            try {
                if (account.isEmpty()) {
                    System.out.println(error);

                    Thread.sleep(1000); // Pause for 1 second to allow the user to read the message
                    return true;
                    
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
               // Return false if the thread is interrupted
            }
            return false; 
        }
    }
    // Ensures the opening balance is valid before creating a new account.
    // This prevents zero or negative balances from being accepted.
    public static double AmountValidation() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(" Enter  Amount ");
            try {
                double amount = input.nextDouble();

                // A negative or zero opening balance is not allowed.
                if (amount <= 0) {
                    System.out.println(" Amount Should not be Negative or 0 :");
                    continue;
                } else {
                    // Return the valid balance only after validation passes.
                    return amount;
                }
            
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input ");
                input.nextLine(); // Clear the invalid input 
            }
        }
    }
    public static double OpeningAmountValidation() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(" Enter  Amount ");
            try {
                double amount = input.nextDouble();

                // A negative or zero opening balance is not allowed.
                if ( amount < 100) {
                    System.out.println(" Amount Should be Greater than 100 :");
                    continue;
                } else {
                    // Return the valid balance only after validation passes.
                    return amount;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input ");
                input.nextLine(); // Clear the invalid input from the scanner buffer
            }
        }
    }

    // Handles a loan request for an account that implements the Loanable interface.
    public static void requestLoan() {
        Scanner input = new Scanner(System.in);
        while (true) {

            System.out.print(" Enter Account Number : ");
            try {
                int accountNumber = input.nextInt();
                boolean found = false;

                for (BankAccount bank : account) {
                    if (accountNumber == bank.getAccountNumber()) {
                        found = true;
                        
                        System.out.println(" Loan accumulated : " + bank.getTotalLoanAmount());
                        double amount = AmountValidation();
                        System.out.println("  CHECKING LOAN ELIGITIBILITY .....");
                        timeDelay(2000);
                        if (bank instanceof Loanable) { // -this ask the bank object if its classes has instances of
                                                        // loanable
                            Loanable loan = (Loanable) bank; // if yes then cast it store it in a loan variable
                            boolean success = loan.requestLoan(amount);
                            double newBalance = (bank.getBalance());

                            if (success) {
                                System.out.println("        Loan Approved  \n");
                                Transactions.TransactionLog(accountNumber, " Request Loan", amount, newBalance,
                                        "Loan Approved  ", bank.AccountType());

                                System.out.println(" Account     : " + bank.getAccountNumber());
                                System.out.println(" Loan Amount : M " + amount);

                                System.out.println(" New Balance : M " + newBalance);
                                bank.setBalance(newBalance);
                                System.out.println();
                                SaveAccount.savingAccount(account);
                                return;

                            } else {
                                System.out.println("Loan Not Approved \n");
                                Transactions.TransactionLog(accountNumber, " Request Loan", amount, newBalance,
                                        "Loan Not Approved  ", bank.AccountType());
                                timeDelay(1000);
                                return ;
                                
                            }

                        }
                    } 
                }
                if(!found){
                    System.out.println("Account Not Found ");
                    continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("  Invalid Input ");
                input.nextLine();
            }
        }
    }

    // Calculates and prints the interest earned for the selected account.
    // The account-specific implementation decides whether interest is zero or
    // positive.
    
public static void calculateInterest() {
    Scanner input = new Scanner(System.in);
    
    while (true) {
        System.out.print(" Enter Account Number : ");

        try {
            int accountNumber = input.nextInt();
            boolean success = false;
            boolean iscurrent = false;

            // Search for the account
            for (BankAccount bank2 : account) {
                if (accountNumber == bank2.getAccountNumber()) {

                    success = true;

                    // Check account type first
                    if (bank2.AccountType().equalsIgnoreCase("Current Account")) {
                        iscurrent = true;
                        System.out.println("Current accounts do not earn interest.");
                        break;
                      
                    }

                    double Balance_Old = bank2.getBalance();

                    // Calculate interest only once
                    double interest = bank2.calculateInterest();
                    double newBalance = Balance_Old + interest;

                    bank2.setBalance(newBalance);

                    System.out.println();
                    System.out.println(" Calculated Interest \n");
                    System.out.println(" Account Type : "+ bank2.AccountType());

                    System.out.println(" Current Balance : "+ Balance_Old);

                    System.out.println(" Interest Rate : 5%");

                    System.out.println(" Interest Earned : M"+ interest);

                    System.out.println(" New Balance : M"+ newBalance);

                    Transactions.TransactionLog(accountNumber,"Interest",Balance_Old,newBalance,"Interest Calculated Successfully",
                        bank2.AccountType()
                    );

                    SaveAccount.savingAccount(account);
                    return;
                }
            }

            // Check only after the entire list is searched
            if (!success) {
                System.out.println("Account Not Found");
                continue;
            }
            if (!iscurrent) {
                continue;
            }
            

        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            input.nextLine();
        }
    }
}


    // Moves money from one account to another after validating both accounts.
    // The source account is debited first, then the destination account is
    // credited.
    public static void transfer() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("FROM : ");
            try {
                int accountNumber = input.nextInt();
                Boolean found = false;
                for (BankAccount bank : account) {
                    if (accountNumber == bank.getAccountNumber()) {
                        found = true;
                        while (true) {
                            System.out.print(" TO : ");
                            try {
                                int accountNumber1 = input.nextInt();
                                // Prevents sending money to the same account.
                                if (accountNumber1 == accountNumber) {
                                    System.out.println(" You Can Not Transfer From They Same Account ");
                                    continue;
                                }
                                boolean found1 = false;
                                for (BankAccount bank1 : account) {
                                    if (accountNumber1 == bank1.getAccountNumber()) {
                                        found1 = true;
        
                                        double amount = AmountValidation();
                                        // Withdraw from the sender using that account's rules.
                                        boolean success = bank.withdraw(amount);

                                        if (success) {
                                            // Deposit into the receiver only if the withdrawal succeeded.
                                            boolean deposited = bank1.deposit(amount);

                                            if (deposited) {
                                                System.out.println("Transfer successful");
                                                SaveAccount.savingAccount(account);


                                                Transactions.TransactionLog(accountNumber, "Transfer Out ", amount,
                                                        bank.getBalance(), "Transfer successful To " + accountNumber1,
                                                        bank.AccountType());
                                                Transactions.TransactionLog(accountNumber1, "Transfer IN ", amount,
                                                        bank1.getBalance(),
                                                        "Transfer received successful From " + accountNumber,
                                                        bank1.AccountType());

                                            } else {
                                                bank.deposit(amount); // Return money to sender
                                                System.out.println("Transfer failed");
                                                SaveAccount.savingAccount(account);
                                                timeDelay(1000);
                                            }

                                        } else {
                                            // If the source account cannot cover the amount, the transfer is cancelled.
                                            System.out.println("Transfer cancelled Low Balance ");
                                            timeDelay(1000);
                                            Transactions.TransactionLog(accountNumber, "Transfer ", amount,
                                                    bank.getBalance(), "Transfer cancelled Low Balance ",
                                                    bank1.AccountType());
                                        }

                                        break;
                                    }
                                }
                                if (!found1) {
                                    System.out.println(" Invalid Account Try Again ");
                                    displayOtherAvailableAccounts(accountNumber);
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println(" Invalid Input ");
                                input.nextLine();
                            }
                        }
                    }
                }
                if (!found) {
                    System.out.println(" Invalid Account Try Again ");
                   
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Invalid Input ");
                input.nextLine();
            }
        }

    }

    // Adds money into the chosen account if the user input is valid.
    // A positive amount is only accepted because negative deposits are not allowed.
    public static void deposit() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Enter Account Number : ");
            try {
                int number = input.nextInt();
                Boolean found = false;
                for (BankAccount bank : account) {
                    if (number == bank.getAccountNumber()) {
                        found = true;
                        System.out.print(" Deposit Amount: ");
                        double amount = input.nextDouble();
                        boolean success = bank.deposit(amount);

                        if (success) {
                            // The balance increases only when the deposit value is valid.
                            System.out.println(" Amount Successfully Deposited ");
                            System.out.println(" New Balance : M" + bank.getBalance()+"\n");

                            SaveAccount.savingAccount(account);

                            Transactions.TransactionLog(number, "Deposit", amount, bank.getBalance(),
                                    "Amount Successfully Deposited", bank.AccountType());
                        } else {
                            // Invalid amount is rejected and recorded as a failed deposit attempt.
                            System.out.println(" CAN'T Deposite A Negative Amount ");
                            Transactions.TransactionLog(number, "Deposit", amount, bank.getBalance(),
                                    "CAN'T Deposite A Negative Amount or Zero", bank.AccountType());
                        }

                        break;
                    }

                }
                if (!found) {
                    System.out.println(" Account Not Found Try again: ");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input :");
                input.nextLine();
            }
        }
    }

    // Removes money from an account while respecting the account's withdrawal
    // rules, such as minimum balance and overdraft limits.
    public static void withdraw() {
        
        Scanner input = new Scanner(System.in);
        while (true) {

            System.out.print("Enter Account Number : ");
            try {
                int number = input.nextInt();
                Boolean found = false;
                for (BankAccount bank : account) {
                    if (number == bank.getAccountNumber()) {
                        found = true;

                        Boolean success = false;
                        while (true) {
                            System.out.print(" Withdraw Amount: ");
                            try {
                                double amount = input.nextDouble();
                                // The actual withdrawal rules live in each account type.
                                success = bank.withdraw(amount);

                                if (success) {
                                    // Successful withdrawals are saved to the transaction log.
                                    Transactions.TransactionLog(number, "Withdraw", amount, bank.getBalance(),
                                            "Amount Withdraw Successfully ", bank.AccountType());
                                    SaveAccount.savingAccount(account);

                                } else {
                                    // Failed withdrawals usually mean the account would fall below its limit.
                                    Transactions.TransactionLog(number, "Withdraw", amount, bank.getBalance(),
                                            "Savings must maintain 100", bank.AccountType());

                                    continue;
                                }

                                timeDelay(1000);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println(" Input Error");
                                input.nextLine();
                            }
                        }

                    }
                }
                if (!found) {
                    System.out.println(" Invalid Account Number Try Again :");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Invalid Input ");
                input.nextLine();
            }
        }
    }

    // Collects the details needed to create a savings account.
    // This method stores the new account in the shared list and prints the result.
    public static void getUserSavingsDetails() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(" Enter Account Holder:");
                String Name = input.nextLine();
                System.out.println(" Enter Account Number (Must be 9 digits): ");
                int choice = accountNumber();
                

                System.out.print(" Enter Opening Balance : ");
                double amount = OpeningAmountValidation();
                System.out.println();

                // Create a new SavingsAccount and add it to the program's account list.
                BankAccount bank = new SavingsAccount(choice, amount, Name);
                
                account.add(bank);

                System.out.println(" Account created successfully! ");
                Transactions.TransactionLog(choice, Name, amount, amount, " Account created successfully! ", "Savings");
                System.out.println();
                bank.display();
                System.out.println();
                SaveAccount.savingAccount(account);
               
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invaid input Try again: ");
                input.nextLine();
            }
        }

    }

    // Collects the details needed to create a current account.
    // Current accounts are useful for customers who need overdraft support.
    public static void getUserCurrentAccountDetails() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(" Enter Account Holder:");
                String Name = input.nextLine();
                System.out.println(" Enter Account Number (Must be 9 digits): ");
                int choice = accountNumber();
                System.out.print(" Enter Opening Balance : ");
                double amount = AmountValidation();
                System.out.println();

                // Create and store the current account in the global account list.
                BankAccount bank = new CurrentAccount(choice, amount, Name);
                account.add(bank);

                System.out.println(" Account created successfully! ");
                Transactions.TransactionLog(choice, Name, amount, amount, "Account created successfully!", "Current");
                System.out.println();
                
                SaveAccount.savingAccount(account);
                bank.display();

                
              
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invaid input Try again: ");
                input.nextLine();
            }
        }

    }

    // Displays the main menu and routes the selected action to the relevant method.
    // This keeps the user interaction organized and easy to follow.
    public static void menu() {
        Scanner input = new Scanner(System.in);
        while (true) {

            // Show all the available banking operations to the user.
            System.out.println("    WELCOME TO LUCT BANK SYSTEM   ");
            System.out.println("           MAIN MENU   \n");
            System.out.println("1. Create New Account ");
            System.out.println("2. Display All Account ");
            System.out.println("3. Deposite Money ");
            System.out.println("4. Withdraw Money ");
            System.out.println("5. Transfer Money ");
            System.out.println("6. Calculate Interest ");
            System.out.println("7. Request Bank Loan ");
            System.out.println("8. Display Account ");
            System.out.println("9. Exit ");
            System.out.println(" ");
            System.out.print(" Enter Your Choice : ");

            try {
                int choice = input.nextInt();

                switch (choice) {
                    case 1:

                        while (true) {

                            // The user chooses the account type before creating it.
                            System.out.println(" 1. Savings Account ");
                            System.out.println(" 2. Current Account ");
                            try {
                                choice = input.nextInt();

                                if (choice == 1) {
                                    getUserSavingsDetails();
                                }
                                else if (choice == 2) {
                                    getUserCurrentAccountDetails();
                                }
                                else {
                                    System.out.println(" Invalid Input, Enter either 1 or 2 Try Again :");
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println(" Enter Vaid Input :");
                                input.nextLine();
                            }


                        }
                        timeDelay(2000);
                        break;

                    case 2:

                        if(CheckAccountEmpty(" Display Failed No Accounts Available.")){
                            break;
                        }


                        // Display every saved account in the list.
                        System.out.println("                             All Bank Account   ");

                        table2.table(account);
                        
                        System.out.println();

                        System.out.print("Press Enter to continue...");
                        String continueInput = input.nextLine(); // Consume the newline character
                        continueInput = input.nextLine(); // Wait for the user to press Enter
                        timeDelay(1000);
                        break;

                    case 3:
                        
                        if(CheckAccountEmpty(" Deposit Failed No Accounts Available.")){
                            break;
                        }
                        System.out.println("  Deposit Money  ");
                        deposit();
                        timeDelay(1000);
                        break;

                    case 4:
                        if(CheckAccountEmpty(" Withdrawal Failed No Accounts Available.")){
                            break;
                        }
                        // Ex
                        System.out.println("  WITHDRAW MONEY ");
                        withdraw();
                        timeDelay(1000);
                        break;

                    case 5:
                        if( account.size() < 2){
                            System.out.println(" Transfer Failed  Accounts Available is less than 2.There should be atleast 2 accounts to transfer money.");
                            timeDelay(1000);
                            break;
                        }
                        System.out.println("  Transfer Money ");
                        transfer();
                        timeDelay(1000);
                        break;

                    case 6:
                        if(CheckAccountEmpty(" Calculate Interest Failed No Accounts Available.")){
                            break;
                        }

                        System.out.println("  Calculate Interest ");
                        calculateInterest();
                        System.out.println();
                        timeDelay(1000);
                        break;

                    case 7:
                        if(CheckAccountEmpty(" Request Loan Failed No Accounts Available.")){
                            break;
                        }
                        
                        System.out.println("  REQUEST BANK LOAN  ");
                        requestLoan();
                        break;

                    case 8:
                        if(CheckAccountEmpty(" Display Failed No Accounts Available.")){
                            break;
                        }
                        // Exit the application cleanly when the user selects option 8.
                        System.out.println("  Display Account \n ");

                        System.out.println("Enter Account Number ");
                        int acc = input.nextInt();
                        

                        for (BankAccount bankAccount : account) {
                            if (acc == bankAccount.getAccountNumber()) {
                                bankAccount.display();
                            }
                        }
                        System.out.println("");
                        timeDelay(2000);
                        break;

                    case 9:
                        System.out.println("   EXITING  ");
                        break;

                    default:
                        System.out.println("Invalid menu choice. Try again.");
                        // If no valid menu item is selected, the menu is simply shown again.
                        break;

                }
                if (choice == 9) {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input :");
                input.nextLine();
            }

        }

    }

    public static void main(String[] args) throws Exception {
        menu();
   }
}