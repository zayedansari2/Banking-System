import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BankSystem extends Accounts {

    Scanner input = new Scanner(System.in);
    Random random = new Random();
    private static final String FILE_PATH = "src/accounts.txt";

    public void CreateAccount() {
        System.out.print("Enter Your Full Name: ");
        String name = input.nextLine();
        setAccountName(name);

        int accountNumber = random.nextInt(900000000) + 100000000;
        setAccountNumber(accountNumber);

        System.out.print("Set a 4-digit PIN for your account: ");
        int pin = input.nextInt();
        setAccountPin(pin);

        saveAccountToFile();

        System.out.println("Account Successfully Created!");
        System.out.println("Below Are Your Account Details: ");
        System.out.println("Account Name: " + getAccountName());
        System.out.println("Account Number: " + getAccountNumber());
    }

    public void checkBalance(int accountNumber, int pin) {
        if (validateAccount(accountNumber, pin)) {
            System.out.println("Welcome, " + getAccountName() + "!");
            System.out.println("Your balance is: " + getBalance());
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    public void deposit(int accountNumber, int pin, int amount) {
        if (validateAccount(accountNumber, pin)) {
            setBalance(getBalance() + amount);
            saveAccountToFile();
            System.out.println("Deposit successful. Your new balance is: " + getBalance());
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    public void withdraw(int accountNumber, int pin, int amount) {
        if (validateAccount(accountNumber, pin)) {
            if (amount <= getBalance()) {
                setBalance(getBalance() - amount);
                saveAccountToFile();
                System.out.println("Withdrawal successful. Your new balance is: " + getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    private boolean validateAccount(int accountNumber, int pin) {
        loadAccountFromFile(accountNumber);
        return getAccountNumber() == accountNumber && getAccountPin() == pin;
    }

    private void saveAccountToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(getAccountNumber() + "," + getAccountPin() + "," + getAccountName() + "," + getBalance());
        } catch (IOException e) {
            System.out.println("Error saving account information.");
        }
    }

    private void loadAccountFromFile(int accountNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == accountNumber) {
                    setAccountNumber(Integer.parseInt(parts[0]));
                    setAccountPin(Integer.parseInt(parts[1]));
                    setAccountName(parts[2]);
                    setBalance(Integer.parseInt(parts[3]));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading account information.");
        }
    }
}
