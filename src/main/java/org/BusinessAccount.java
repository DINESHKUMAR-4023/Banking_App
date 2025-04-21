package org;

public class BusinessAccount extends BankAccount{
    StringBuilder sql;
    private int overdraftLimit;

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(int overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public BusinessAccount(){
        super();
    }
    public BusinessAccount(DbConnectivity db){
        super(db);
    }

    @Override
    public void withdraw(String acNo, int balance, int amount, int overdraftLimit){
        this.overdraftLimit = overdraftLimit;
        int withdrawLimit = balance + overdraftLimit;
        int balanceAfterWithdraw = balance - amount;
        if (amount <= withdrawLimit){
            sql = new StringBuilder("Update BANK_ACCOUNTS Set Account_Balance = ? Where Account_Number = ?");
            db.executeUpdateOrInsertStatement(sql,balanceAfterWithdraw, acNo);
        }
        else{
            System.out.println("Insufficient amount/ Overdraft amount breached");
        }
    }
}
