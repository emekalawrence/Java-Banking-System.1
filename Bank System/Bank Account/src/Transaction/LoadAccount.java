package Transaction;

import Bank1.BankAccount;
import Savings.CurrentAccount;
import Savings.SavingsAccount;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LoadAccount {
    public static ArrayList<BankAccount> loadAccounts( Set<Integer> accountSet ) {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        try {
            File file = new File("Accounts.txt");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                String[] parts = line.split(",");

                int accountNumber = Integer.parseInt(parts[0]);
                double loanAmount = Double.parseDouble(parts[1]);
                double balance = Double.parseDouble(parts[2]);
                String name = parts[3];
                String accountType = parts[4];

                BankAccount account;
                if (accountType.equals("Savings Account")) {
                    account = new SavingsAccount(accountNumber, balance, name);
                } else if (accountType.equals("Current Account")) {
                    account = new CurrentAccount(accountNumber, balance, name);
                } else {
                    continue; // Skip unknown account types
                }

                account.addLoanAmount(loanAmount); // Set the loan amount for the account
                accounts.add(account);
                accountSet.add(accountNumber);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Accounts file not found. Starting with an empty list.");
        }

        return accounts;
    }

}
