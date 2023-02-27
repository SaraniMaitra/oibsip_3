import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private double balance;
    private ArrayList<String> transactionHistory;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<String>();
    }

    public void showTransactionHistory() {
        if (transactionHistory.size() == 0) {
            System.out.println("Transaction history is empty.");
        } else {
            System.out.println("Transaction history:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            String transaction = "Withdrawal: " + amount;
            transactionHistory.add(transaction);
            System.out.println("Withdrawal successful. Current balance: " + balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        String transaction = "Deposit: " + amount;
        transactionHistory.add(transaction);
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    public void transfer(double amount, ATM recipient) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            recipient.balance += amount;
            String transaction = "Transfer: " + amount + " to " + recipient.toString();
            transactionHistory.add(transaction);
            System.out.println("Transfer successful. Current balance: " + balance);
        }
    }

    public void quit() {
        System.out.println("Thank you for using this ATM.");
        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to this ATM.");
        System.out.print("Please enter your initial balance: ");
        double initialBalance = scanner.nextDouble();
        ATM atm = new ATM(initialBalance);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    atm.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter recipient's initial balance: ");
                    double recipientInitialBalance = scanner.nextDouble();
                    ATM recipient = new ATM(recipientInitialBalance);
                    atm.transfer(transferAmount, recipient);
                    break;
                case 5:
                    atm.quit();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}