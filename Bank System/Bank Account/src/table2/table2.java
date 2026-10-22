package table2;

import Bank1.BankAccount;
import java.util.ArrayList;

public class table2 {

    public static void table(ArrayList<BankAccount> accounts) {
    

       
   

        

        System.out.print("+");
        System.out.print("-".repeat(5));
        System.out.print("+");
        System.out.print("-".repeat(15));
        System.out.print("+");
        System.out.print("-".repeat(20));
       
        System.out.print("+");
        System.out.print("-".repeat(15));
        System.out.print("+");
        System.out.print("-".repeat(15));
      
        System.out.print("+");
        System.out.print("-".repeat(17));
        System.out.print("+ \n");
        
        System.out.print("| ");
        System.out.print("NO:"+" |".repeat(1));
        System.out.print(" Name"+" ".repeat(9)+" |".repeat(1));

        System.out.print(" Account Number"+" ".repeat(4)+" |".repeat(1));
        System.out.print(" Loan Amount"+" ".repeat(2)+" |".repeat(1));

        System.out.print(" Balance "+" ".repeat(5)+" |".repeat(1));
        System.out.print(" Account Type "+" ".repeat(3)+"| \n");

        System.out.print("+");
        System.out.print("-".repeat(5));

        System.out.print("+");
        System.out.print("-".repeat(15));
        System.out.print("+");
        System.out.print("-".repeat(20));
       
        System.out.print("+");
        System.out.print("-".repeat(15));
        System.out.print("+");
        System.out.print("-".repeat(15));
      
        System.out.print("+");
        System.out.print("-".repeat(17));
        System.out.print("+ \n");

        for(BankAccount account : accounts) {
            System.out.print("| ");
            System.out.printf("%-3d |", accounts.indexOf(account) + 1);
            System.out.printf(" %-13s |", account.getName());
            System.out.printf(" %-18d |", account.getAccountNumber());
            System.out.printf(" %-13.2f |", account.getTotalLoanAmount());
            System.out.printf(" %-13.2f |", account.getBalance());
            System.out.printf(" %-13s | \n", account.AccountType());

            
        System.out.print("+");
        System.out.print("-".repeat(5));
        System.out.print("+");
        System.out.print("-".repeat(15));
        System.out.print("+");
        System.out.print("-".repeat(20));
       
        System.out.print("+");
        System.out.print("-".repeat(15));
        System.out.print("+");
        System.out.print("-".repeat(15));
      
        System.out.print("+");
        System.out.print("-".repeat(17));
        System.out.print("+ \n");
        }

    }
}

