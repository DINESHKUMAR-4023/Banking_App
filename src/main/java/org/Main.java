package org;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbConnectivity db = new DbConnectivity();
        Bank bank = new Bank(db);
        UserOperations userOps = new UserOperations(db, bank);
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("*********** ------------ ***********");
            System.out.println();
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            System.out.println();
            System.out.println("*********** ------------ ***********");

            switch (choice){
                case 1:
                    userOps.createAccountUserOps();
                    System.out.println("Account Created");
                    break;
                case 2:
                    userOps.depositUserOps();
                    System.out.println("Money deposited");
                    break;
                case 3:
                    userOps.withdrawUserOps();
                    System.out.println("Money Withdrawn");
                    break;
                case 4:
                    userOps.checkBalanceUserOps();
                    System.out.println("Balance Fetched");
                    break;
                case 5:
                    bank.displayAllAccount();
                    System.out.println("Accounts Shown");
                    break;
            }
        } while (choice!=6);
    }
}