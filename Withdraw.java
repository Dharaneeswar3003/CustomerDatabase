import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;
    private double currentBalance;

/*
 * Requires: 'amount' is a non-negative double, 'date' is a valid Date object, 'account' is non-null, 'currentBalance' is a valid balance after the withdrawal.
 * Modifies: None
 * Effects:  Creates a new Withdraw instance with the given parameters.
 */
    Withdraw(double amount, Date date, String account, double currentBalance){
        this.amount = amount;
        this.date = date;
        this.account = account;
        this.currentBalance = currentBalance;
    }
    //Getters and Setters...
    public double getamount() {
        return amount;
    }
    public void setamount(double amount) {
        this.amount = amount;
    }
     public String getaccount() {
        return account;
    }
    public void setaccount(String account) {
        this.account = account;
    }
     public double getcurrentBalance() {
        return currentBalance;
    }
    public void setcurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

/*
 * Requires: None
 * Modifies: None
 * Effects:  Returns a string representation of the withdraw information.
 */
    @Override
    public String toString(){
        return "Withdrawal of $" + amount + " on " + date + " into account: " + account +
        "; Current balance in " + account + " is: $" + currentBalance;
    }
}
