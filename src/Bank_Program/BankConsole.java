package Bank_Program;

import java.text.NumberFormat;
import java.util.Scanner;

public class BankConsole {
    private final static String MENU =
            "Please choose your option.\n"
                    + "\t1. Check Balance\n"
                    + "\t2. Deposit\n"
                    + "\t3. Withdraw\n"
                    + "\t4. Previous Transaction\n"
                    + "\t5. Exit\n"
                    + "Option: ";

    private BankAccount account;
    private static NumberFormat format;
    private static Scanner scan;

    public BankConsole() {
        this.account = new BankAccount();
        format = NumberFormat.getCurrencyInstance();
        scan = new Scanner(System.in);
    }

    public void launch() {
        System.out.println("Welcome to Bank!");

        int operation;
        System.out.print(MENU);
        while (true) {
            operation = readNum();
            switch (operation) {
                case 1:
                    printAccountBalance();
                    break;
                case 2:
                    accountDeposit();
                    break;
                case 3:
                    accountWithdraw();
                    break;
                case 4:
                    prevTransaction();
                    break;
                case 5:
                    System.out.println("Thank you for using Bank. Have a nice day!");
                    return;
                default:
                    System.out.println("Please choose an operation from 1-5.");
            }

            System.out.println();
            System.out.print("What is your next option?: ");
        }
    }

    private int readNum() {
        String input = scan.next();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void printAccountBalance() {
        System.out.printf("Current Balance: %s\n", format.format(account.getBalance()));
    }

    private void accountDeposit() {
        System.out.print("How much would you like to deposit?: $");
        double depositAmount = scan.nextDouble();
        if( account.deposit(depositAmount) )
            System.out.printf("Successfully deposited %s into your account.\n", format.format(depositAmount));
        else
            System.out.println("You can only deposit more than $0.");
    }

    private void accountWithdraw() {
        printAccountBalance();
        System.out.print("How much would you like to withdraw?: $");
        double withdrawAmount = scan.nextDouble();
        if(account.withdraw(withdrawAmount)) {
            System.out.printf("Successfully withdrawn %s from your account.\n", format.format(withdrawAmount));
        }
        else
            System.out.printf("You can only withdraw $0.01-%s from your account.\n", format.format(account.getBalance()));
    }

    private void prevTransaction() {
        System.out.println(account.getPreviousTransaction());
    }

    public static void main(String[] args) {
        new BankConsole().launch();
    }
}