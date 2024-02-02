import java.util.Date;
import java.util.Scanner;

/**
 * Requires: None
 * Modifies: this,'scanner', 'today', 'customer', and the balances in 'customer'.
 * Effects:  Interacts with the user to perform deposit, withdrawal, and exit operations
 * Displays customer details and transactions upon exit.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //To create a new customer...
        Customer customer = new Customer("John Doe", 123456, 1000.0, 2000.0);
        //Prints the customer details...
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Customer Details:" + "\nCustomer name: " + customer.getname() + "\nAccount Number: " + customer.getaccountNumber() + 
        "\nChecking account Balance: " + customer.getcheckBalance() + "\nSaving account Balance: " + customer.getsavingBalance());
      
        
        boolean continueTransactions = true;

        while (continueTransactions) {
            Date today = new Date();
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("Choose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");

            int option = scanner.nextInt();
            switch (option) {
                case 1:  //If user chooses to deposit...
                System.out.print("Enter deposit amount: $");
                double depositAmount;
                while (true) {
                    if (scanner.hasNextDouble()) { //To validate for the amount entered by the user
                        depositAmount = scanner.nextDouble();
                        if (depositAmount >= 0) {
                            break;
                        } else {
                            System.out.print("Amount must be a non-negative value. Please try again: $");
                             //Does not take negative input as amount must always be positive.
                        }
                    } else {
                        System.out.print("Invalid input. Please enter a numeric value: $");  //Only take double values for amount.
                        scanner.next(); // Consume the invalid input
                    }
                }
                int depositAccountType;
                do { // To validate for the account type.
                    System.out.print("Choose account type (1 for Checking, 2 for Saving): ");
                    
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Please choose again: ");
                        scanner.next(); // Consume the invalid input
                    }
                
                    depositAccountType = scanner.nextInt();
                
                    if (depositAccountType != 1 && depositAccountType != 2) {
                        System.out.print("Invalid account type. Please choose again: ");
                        scanner.next();
                    }
                } while (depositAccountType != 1 && depositAccountType != 2); // The loop will continue as long as the account type is not equal to 1 or 2.

                String Depositaccount = (depositAccountType == 1) ? Customer.CHECKING : Customer.SAVING; //coverts the account type to string account and assigns correspnding value.
                customer.deposit(depositAmount, today, Depositaccount);
                break;
                case 2:  //If user chooses to withdraw...
                System.out.print("Enter withdrawal amount: $");
                double withdrawalAmount;
                while (true) { //To validate for the amount entered by the user.
                    if (scanner.hasNextDouble()) {
                        withdrawalAmount = scanner.nextDouble();
                        if (withdrawalAmount >= 0) {
                            break;
                        } else { 
                            System.out.print("Amount must be a non-negative value. Please try again: $");
                            //Does not take negative input as amount must always be positive.
                        }
                    } else {
                        System.out.print("Invalid input. Please enter a numeric value: $"); //Only take double values for amount.
                        scanner.next(); // Consume the invalid input
                    }
                }
                int withdrawalAccountType;
                do {  //To validate for the account type.
                    System.out.print("Choose account type (1 for Checking, 2 for Saving): ");
                    
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Please choose again: ");
                        scanner.next(); // Consume the invalid input
                    }
                    withdrawalAccountType = scanner.nextInt();

                    if (withdrawalAccountType != 1 && withdrawalAccountType != 2) {
                        System.out.print("Invalid account type. Please choose again: ");
                        scanner.next();
                    }
                } while (withdrawalAccountType != 1 && withdrawalAccountType != 2); //The loop wil continue as long as the account type is not equal to 1 or 2.

                String Withdrawaccount = (withdrawalAccountType == 1) ? Customer.CHECKING : Customer.SAVING; //coverts the account type to string account and assigns correspnding value.
                customer.withdraw(withdrawalAmount, today, Withdrawaccount);
                break;
                case 3:  //If user chooses to exit the program...
                System.out.println("Exiting the program");
                // Displaying deposits and withdrawals once the user exits the program...
                System.out.println("\nTransactions made:");
                System.out.println("Deposits:");
                customer.displayDeposits();
            
                System.out.println("\nWithdrawals:");
                customer.displayWithdraws();
                    
                continueTransactions = false;
                break;
                default:
                System.out.println("Invalid option. Please choose again.");
            } 
        }
    scanner.close();
    }
}
