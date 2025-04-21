package org;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private int balance;
    DbConnectivity db;
    StringBuilder sql = new StringBuilder();

    public BankAccount(){}

    public BankAccount (DbConnectivity db){
        this.db = db;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public int getBalance(String acNo) {
        sql = new StringBuilder("Select Account_Balance from BANK_ACCOUNTS where Account_Number = ?");
        ResultSet resultSet = db.executeQueryWithPlaceHolders(sql, acNo);
        try {
            if (resultSet.next()){
                balance = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(String acNo, int amount){
        sql = new StringBuilder("Select Account_Balance From BANK_ACCOUNTS where Account_Number = ?");
        ResultSet resultSet = db.executeQueryWithPlaceHolders(sql, acNo);
        sql.setLength(0);
        try {
            if (resultSet.next()) {
                balance = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        balance+=amount;
        sql = new StringBuilder("Update BANK_ACCOUNTS Set Account_Balance = ? Where Account_Number = ?" );
        db.executeUpdateOrInsertStatement(sql, balance, acNo);
        sql.setLength(0);
    }

    public void withdraw(String acNo, int amount){
        sql = new StringBuilder("Select Account_Balance, Account_Type, RateOrLimit From BANK_ACCOUNTS where Account_Number = ?");
        ResultSet resultSet = db.executeQueryWithPlaceHolders(sql,acNo);
        sql.setLength(0);
        String type =null;
        int rateOrLimit = 0;
        try{
            if (resultSet.next()){
                balance = resultSet.getInt("Account_Balance");
                type = resultSet.getString("Account_Type");
                rateOrLimit = resultSet.getInt("RateOrLimit");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if ("Business".equalsIgnoreCase(type)){
            new BusinessAccount(db).withdraw(acNo, balance, amount, rateOrLimit);
        }
        else withdraw(acNo, balance, amount, rateOrLimit);

        if (balance >= amount){
            balance-=amount;
        }
        else System.out.println("insufficient balance");
    }

    public void withdraw(String acNo, int balance, int amount, int rateOrLimit) {
        int balanceAfterWithdraw = balance - amount;
        if (balance >= amount){
            sql = new StringBuilder("Update BANK_ACCOUNTS Set Account_Balance = ? Where Account_Number = ?");
            db.executeUpdateOrInsertStatement(sql, balanceAfterWithdraw, acNo);
            sql.setLength(0);
        }
        else System.out.println("insufficient balance");
    }

}
