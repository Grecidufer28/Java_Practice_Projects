package Bank_Program;

import java.text.NumberFormat;

public class BankAccount {
    private static double balance;
    private static NumberFormat format;
    private static String prevTransaction;

    private final static int DEFAULT_BALANCE = 100;

    public BankAccount() {
        balance = DEFAULT_BALANCE;
        format = NumberFormat.getCurrencyInstance();
        prevTransaction = "No transactions have been made.";
    }

    public BankAccount(double balance) {
        this.balance = balance;
        format = NumberFormat.getCurrencyInstance();
        prevTransaction = "No transactions have been made.";
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            prevTransaction = "You've last deposited " + format.format(amount) + ".";
            return true;
        }
        else
            return false;
    }

    public boolean withdraw(double amount) {
        if(0 < amount && amount <= balance) {
            balance -= amount;
            prevTransaction = "You've last withdrawn " + format.format(amount) + ".";
            return true;
        }
        else
            return false;
    }

    public String getPreviousTransaction() {
        return prevTransaction;
    }
}