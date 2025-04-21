package org;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.*;

public class Bank {
    private List<BankAccount> accounts = new ArrayList<>();
    DbConnectivity db;
    StringBuilder sql = new StringBuilder();

    public Bank(DbConnectivity db){
        this.db = db;
    }

    public boolean createAccount(String accountType, String accountHolderName, double rateOrLimit){
        sql = new StringBuilder("Select count(Account_number) from BANK_ACCOUNTS;");
        ResultSet resultSet = db.getSelectResultSet(sql.toString());
        sql.setLength(0);
        int accountCount = 0;
        try {
            if (resultSet.next()){
                accountCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         String accountNumber = "Acc_" + (accountCount+1);
         if (accountType.equalsIgnoreCase("savings")){
             sql = new StringBuilder("Insert into BANK_ACCOUNTS (Account_Number, Account_Holder_Name, Account_Balance, Account_Type, RateOrLimit)" +
                     "Values(?, ?, null, 'Savings', ?);");
             db.executeUpdateOrInsertStatement(sql, accountNumber, accountHolderName, rateOrLimit);
             return true;
         } else if (accountType.equalsIgnoreCase("business")) {
             sql = new StringBuilder("Insert into BANK_ACCOUNTS (Account_Number, Account_Holder_Name, Account_Balance, Account_Type, RateOrLimit)" +
                     "Values(?, ?, null, 'Business', ?);");
             db.executeUpdateOrInsertStatement(sql, accountNumber, accountHolderName, rateOrLimit);
             return true;
         }
         else {
             System.out.println("invalid choice");
             return false;
         }
    }

    public boolean findAccount(String reqAccountNumber){
        String sql = "Select Account_Number, Account_Holder_Name from BANK_ACCOUNTS;";
        HashMap<String,String> acNumName = new HashMap<>();
        ResultSet resultSet = db.getSelectResultSet(sql);
        try {
            while (resultSet.next()){
                String accountNumber = resultSet.getString("Account_Number");
                String accountHolderName = resultSet.getString("Account_Holder_Name");
                acNumName.put(accountNumber,accountHolderName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<String, String> e : acNumName.entrySet()){
            if (e.getKey().equals(reqAccountNumber)){
                System.out.println("Account found : "+e.getKey());
                System.out.println("Holder : "+ e.getValue());
                return true;
            }
        }
        return false;
    }

    public void displayAllAccount(){
        String sql = "Select * from BANK_ACCOUNTS;";
        ResultSet resultSet = db.getSelectResultSet(sql);
        try {
            while(resultSet.next()){
                String accountNumber = resultSet.getString("Account_Number");
                String accountHolderName = resultSet.getString("Account_Holder_Name");
                int accountBalance = resultSet.getInt("Account_Balance");
                String accountType = resultSet.getString("Account_Type");

                System.out.println("Account_Number : "+accountNumber+
                        "\nAccount_Holder_Name : "+accountHolderName+
                        "\nAccount_Balance : "+accountBalance+
                        "\nAccount_Type : "+accountType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayAllAccountNumber(){
        String sql = "Select Account_Number from BANK_ACCOUNTS;";
        ResultSet resultSet = db.getSelectResultSet(sql);
        try{
            while (resultSet.next()){
                String accountNumber = resultSet.getString("Account_Number");
                System.out.println("Account_Number : "+accountNumber);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
