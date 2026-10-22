package Transaction;

import Bank1.BankAccount;
import java.io.*;
import java.util.ArrayList;

public class SaveAccount {
    public static void savingAccount(ArrayList<BankAccount> accounts) {
        try {
            FileWriter writer = new FileWriter("Accounts.txt", false);

            for (BankAccount account : accounts) {
                writer.write(account.getAccountNumber() + "," + account.getTotalLoanAmount() + "," + account.getBalance() + "," + account.getName() + ","
                        + account.AccountType() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error Writing Accounts ");
        }
    }

}
