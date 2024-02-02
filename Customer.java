import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;  //OVERDRAFT limmit is -100.

    //Getters and Setters...
    public ArrayList<Deposit> getdeposits() {
        return deposits;
    } 
    public ArrayList<Withdraw> getwithdraws() {
        return withdraws;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public int getaccountNumber() {
        return accountNumber;
    }
    public void setaccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getcheckBalance() {
        return checkBalance;
    }
    public void setcheckBalance(double checkBalance) {
        this.checkBalance = checkBalance;
    }
    public double getsavingBalance() {
        return savingBalance;
    }
    public void setsavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }
    //Constructor...
    /*
     * Requires: 'name' is non-dull, 'accountNumber' is a positive integer, 'checkDeposit' and 'savingDeposit' are non-negative doubles.
     * Modifies: this, deposits, withdraws.
     * Effects: creates a new Customer instance with the given parameters.
     */
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkBalance = checkDeposit;
        this.savingBalance = savingDeposit;
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
    }
    //Method to deposit amount...
    /*
     * Requires: 'amt' is a non-negative double, 'date' is a valid Date object,'account' is either Customer.CHECKING or Customer.SAVING.
     * Modifies: this,'checkBalance' or 'savingBalance', deposits list
     * Effects: Returns updated balance after deposit
     */
    public double deposit(double amt, Date date, String account) { 
        if (account.equals(CHECKING)) {  //To deposit in checking account.
            checkBalance += amt;
            deposits.add(new Deposit(amt, date, CHECKING, checkBalance));
            return checkBalance;
        } else if (account.equals(SAVING)) {  //To deposit in savings account.
            savingBalance += amt;
            deposits.add(new Deposit(amt, date, SAVING, savingBalance)); //Adds the deposit to the deposit ArrayList.
            return savingBalance;
        } else {
            System.out.println("Invalid account type.");
            return -1;
        }
    }
    //Method to withdraw amount...
    /*
     * Requires: 'amt' is a non-negative double, 'date' is a valid Date object,'account' is either Customer.CHECKING or Customer.SAVING.
     * Modifies: this,'checkBalance' or 'savingBalance', withdraws list
     * Effects: Returns updated balance after withdrawal
     */
    public double withdraw(double amt, Date date, String account) {
        if (account.equals(CHECKING)) {  //To withdraw from checking account.
            if (checkOverdraft(amt, CHECKING)) {  //Checks if the withdrawal amount exceeds current balance.
                System.out.println("Overdraft! Withdrawal cannot be processed from Checking account.");
                return -1;
            } else {
                checkBalance -= amt;  //Subtracts the withdrawal amount from balance amount in Checking.
                withdraws.add(new Withdraw(amt, date, CHECKING, checkBalance));
                return checkBalance;
            }
        } else if (account.equals(SAVING)) {  //To withdraw from savings account.
            if (checkOverdraft(amt, SAVING)) {  //Checks if the withdrawal amount exceeds current balance.
                System.out.println("Overdraft! Withdrawal cannot be processed from Saving account.");
                return -1;
            } else {
                savingBalance -= amt;  //Subtracts the withdrawal amount from balance amount in Saving.
                withdraws.add(new Withdraw(amt, date, SAVING, savingBalance));
                return savingBalance;
            }
        } else {
            System.out.println("Invalid account type.");
            return -1;
        }
    }
    //Private method for overdraft...
    /*
     * Requires: 'amt' is non-negative double, account is either Customer.CHECKING or Customer.SAVING
     * Modifies: None
     * Effects: Returns true if withdrawal will result in an OVERDRAFT, false otherwise.
     */
    private boolean checkOverdraft(double amt, String account) {
        if (account.equals(CHECKING)) {
            //if checking balance minus the withdrawal amount is less than the overdraft amount, print true or OVERDRAFT.
            return checkBalance - amt < OVERDRAFT;
        } else if (account.equals(SAVING)) {
            //if saving balance minus the withdrawal amount is less than the overdraft amount, print true or OVERDRAFT.
            return savingBalance - amt < OVERDRAFT;
        } else {
            return false;
        }
    }
    //Method to display the deposits made...
    /*
    Requires: None
    Modifies: None
    Effects:  Prints the list of deposits made by the customer.
    */
    public void displayDeposits() {
        if (deposits.isEmpty()) {
            System.out.println("None made");  //Displays "None made" if no deposits are done.
        } else {
            for (Deposit d : deposits) {
            System.out.println(d);
            }
        }
    }
    //Method to display the withdrawals made...
    /*
    * Requires: None
    * Modifies: None
    * Effects:  Prints the list of withdrawals made by the customer.
    */
    public void displayWithdraws() {
        if (withdraws.isEmpty()) {
            System.out.println("None made");  //Displays "None made" if no withdrawals are done.
        } else {
            for (Withdraw w : withdraws) {
            System.out.println(w);
            }
        }
    }
}
