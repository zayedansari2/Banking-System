import java.util.Scanner;

public class App {
    

    public static void main(String[] args) throws Exception {

        BankSystem bank = new BankSystem();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n==============================");
            System.out.println("Welcome to the Banking App");
            System.out.println("==============================");
            System.out.println("Please select an option: ");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    bank.CreateAccount();
                    break;
                case 2:
                    handleCheckBalance(bank, input);
                    break;
                case 3:
                    handleDeposit(bank, input);
                    break;
                case 4:
                    handleWithdraw(bank, input);
                    break;
                case 5:
                    System.out.println("Thank you for using the Banking App. Goodbye!");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleCheckBalance(BankSystem bank, Scanner input) {
        System.out.print("Enter your account number: ");
        int accNum = input.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = input.nextInt();
        bank.checkBalance(accNum, pin);
    }

    private static void handleDeposit(BankSystem bank, Scanner input) {
        System.out.print("Enter your account number: ");
        int accNum = input.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = input.nextInt();
        System.out.print("Enter amount to deposit: ");
        int amount = input.nextInt();
        bank.deposit(accNum, pin, amount);
    }

    private static void handleWithdraw(BankSystem bank, Scanner input) {
        System.out.print("Enter your account number: ");
        int accNum = input.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = input.nextInt();
        System.out.print("Enter amount to withdraw: ");
        int amount = input.nextInt();
        bank.withdraw(accNum, pin, amount);
    }
}
