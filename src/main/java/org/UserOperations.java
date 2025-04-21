package org;

import java.util.Scanner;

public class UserOperations {
    public Bank bank;
    public DbConnectivity db;
    Scanner sc = new Scanner(System.in);
    public BankAccount bankAccount;

    public UserOperations(DbConnectivity db, Bank bank){
        this.db = db;
        this.bank = bank;
    }

    public void createAccountUserOps(){
        try {
            System.out.println("Enter Account Holder Name : ");
            String name = sc.next();
            System.out.println("Enter Account Type (savings/business) : ");
            String type = sc.next();
            double rateOrLimit = 0;
            if (type.equalsIgnoreCase("savings")) {
                System.out.println("Enter the interest rate");
                rateOrLimit = sc.nextDouble();
            } else {
                System.out.println("Enter the overdraft limit");
                rateOrLimit = sc.nextDouble();
            }
            bank.createAccount(type, name, rateOrLimit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  
    }

    public void depositUserOps(){
        System.out.println("");
        System.out.println("Account No.s for reference : ");
        bank.displayAllAccountNumber();
        System.out.println("Enter the Account Number for Deposit : ");
        String acNo = sc.next();
        boolean isAccountExist = bank.findAccount(acNo);
        bankAccount = new BankAccount(db);
        try {
            if (isAccountExist) {
                System.out.println("Enter the amount to Deposit : ");
                int depositAmount = sc.nextInt();
                bankAccount.deposit(acNo, depositAmount);
                System.out.println();
                System.out.println("Balance after deposit : " + bankAccount.getBalance(acNo));
            }
            else {
                System.out.println("Ac- not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
         
    }

    public void withdrawUserOps() {
        System.out.println("");
        System.out.println("Account No.s for reference : ");
        bank.displayAllAccountNumber();
        System.out.println("Enter the Account Number for Withdrawal : ");
        String acNo = sc.next();
        boolean isAccountExist = bank.findAccount(acNo);
        bankAccount = new BankAccount(db);
        try {
        if (isAccountExist) {
            System.out.println("Account Balance : "+bankAccount.getBalance(acNo));
            System.out.println("Enter the amount to Withdraw : ");
            int withdrawAmount = sc.nextInt();
            bankAccount.withdraw(acNo, withdrawAmount);
            System.out.println();
            System.out.println("Balance after withdraw : " + bankAccount.getBalance(acNo));
        }
        else {
            System.out.println("Account_not found");
        }
        } catch (Exception e) {
            System.out.println(e);
        }
         
    }

    public void checkBalanceUserOps(){
        System.out.println();
        System.out.println("Account No. for reference : ");
        bank.displayAllAccountNumber();
        System.out.println("Enter the Account Number to check balance : ");
        String acNo = sc.next();
       boolean isAccountExist = bank.findAccount(acNo);
       bankAccount = new BankAccount(db);
        try{
            if (bankAccount != null){
               int balance = bankAccount.getBalance(acNo);
                System.out.println("Ac No : "+acNo+" Balance : "+balance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
         
    }
}
