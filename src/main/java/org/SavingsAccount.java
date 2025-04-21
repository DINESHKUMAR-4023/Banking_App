package org;

public class SavingsAccount extends  BankAccount{
    private int interestRate;

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public SavingsAccount(DbConnectivity db){
        super(db);
    }

    public void applyInterest(){
        String acNo= null;
       // int interestAmount =  getBalance() * interestRate * 100;
        //deposit(acNo, interestAmount);
    }
}
