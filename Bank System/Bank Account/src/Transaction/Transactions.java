package Transaction;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

// Records each financial activity into the Transactions.txt file.
public final class Transactions {

    // Logs a transaction with the date, account details, amount, balance, and
    // result.

    public static void TransactionLog(int accountNumber, String Transaction_Type, double amount, double balance,
            String result, String Account_Type) {

        // Get the current date and time so each transaction is time-stamped.
        LocalDateTime Now = LocalDateTime.now();
        DateTimeFormatter Format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String DateTime = Now.format(Format);

        try {
            // Append to the transaction log instead of overwriting previous entries.
            FileWriter writer = new FileWriter("Transactions.txt", true);

            writer.write("\n");
            writer.write("...................................................................");
            writer.write("\n");
            writer.write("Date & Time   : " + DateTime);
            writer.write("\n");
            writer.write("Account Number: " + accountNumber);
            writer.write("\n");
            writer.write("Transaction   : " + Transaction_Type);
            writer.write("\n");
            writer.write("Amount        : M " + amount);
            writer.write("\n");
            writer.write("Balance       : M " + balance);
            writer.write("\n");
            writer.write("Result        : " + result);
            writer.write("\n");
            writer.write("Account Type  : " + Account_Type);
            writer.write("\n");
            writer.write("...................................................................\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error Writing Transactions ");
        }
    }

}
