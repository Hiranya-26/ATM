import java.util.Scanner;

public class TheAtm {
    //Starting balance
    static double balance = 1000.00;

    //To track recent 5 transactions
    static String[] history = new String[5];
    static int transactionCount = 0;

    //main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pinNumber = 1224;
        int attempts = 0;

        System.out.println("=== National Savings Bank ===");

        //Running the ATM
        while (attempts < 3) {
            System.out.print("Enter PIN: ");
            int enteredPin = sc.nextInt();

            if (enteredPin == pinNumber) {
                runATM(sc);
                return;
            } else {
                attempts++;
                System.out.println("Wrong PIN. Attempts used: " + attempts + "/3");
            }
        }

        System.out.println("Account Locked. Please contact the branch.");
        sc.close();
    }

    public static void runATM(Scanner sc) {
        boolean isRunning = true;
        System.out.println("\nLogin Successful! Welcome, Jenny");

        while (isRunning) {
            System.out.println("\n----------------------------");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. View Last 5 Transactions");
            System.out.println("5. Exit");
            System.out.print("Select: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: Rs." + balance);
                    break;
                case 2:
                    withdraw(sc);
                    break;
                case 3:
                    deposit(sc);
                    break;
                case 4:
                    viewHistory();
                    break;
                case 5:
                    System.out.println("Thank you for banking with us!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
     // method for withdraw
    public static void withdraw(Scanner sc) {
        System.out.print("Amount to withdraw: Rs.");
        double amount = sc.nextDouble();

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: Rs." + amount);
            System.out.println("Withdrawal successful.");
            System.out.println("Your new account balance is Rs."+balance);
        } else {
            System.out.println("Error: Insufficient funds or invalid amount.");
        }
    }
     //method for deposit
    public static void deposit(Scanner sc) {
        System.out.print("Amount to deposit: Rs.");
        double amount = sc.nextDouble();

        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: Rs." + amount);
            System.out.println("Deposit successful.");
            System.out.println("Your new account balance is Rs."+balance);
        } else {
            System.out.println("Error: Invalid amount.");
        }
    }

   //transactions are added to the history
    public static void addTransaction(String message) {
        if (transactionCount < history.length) {
            history[transactionCount] = message;
            transactionCount++;
        } else {
            // Shift elements to make room for the newest (Advanced)
            for (int i = 0; i < history.length - 1; i++) {
                history[i] = history[i + 1];
            }
            history[history.length - 1] = message;
        }
    }
    //to see the history
    public static void viewHistory() {
        System.out.println("--- Transaction History ---");
        if (transactionCount == 0) {
            System.out.println("No transactions yet.");
        } else {
            for (int i = 0; i < transactionCount; i++) {
                System.out.println((i + 1) + ". " + history[i]);
            }
        }
    }
}
