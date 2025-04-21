CREATE TABLE BANK_ACCOUNTS(
                              Account_Number VARCHAR(20),
                              Account_Holder_Name VARCHAR(20),
                              Account_Balance int
);

ALTER table BANK_ACCOUNTS
    Add Account_Type Varchar(20);

INSERT INTO BANK_ACCOUNTS (Account_Number, Account_Holder_Name, Account_Balance)
Values('Admin_01','Dinesh',5000);

Describe BANK_ACCOUNTS ;

SELECT * from BANK_ACCOUNTS ba

alter table BANK_ACCOUNTS add primary key (Account_Number)

UPDATE BANK_ACCOUNTS
SET Account_Type = 'Savings'
where Account_Number = 'Admin_01';