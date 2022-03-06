package org.codes.view;

import org.codes.beans.Account;
import org.codes.beans.Transaction;
import org.codes.dao.AccountDao;
import org.codes.dao.TxnDao;
import org.codes.utils.HashCode;
import org.codes.utils.Mode;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class LogIn {
    static boolean logInState = false;
    static Account account = null;

    public void loginToAccount(Scanner scanner) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Please enter your credentials to log in.");
        System.out.println("Enter your account number :: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your password :: ");
        String password = scanner.nextLine().strip();
        if (AccountDao.exists(accountNumber) && AccountDao.readPassword(accountNumber).equals(password)) {
            logInState = true;
            System.out.println("Logged into your account, " + accountNumber);
            new LogIn().performUser(scanner, accountNumber);
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    public void performUser(Scanner scanner, int accountNumber) throws SQLException, IOException, ClassNotFoundException {
        account = AccountDao.getAccount(accountNumber);
        while (logInState) {
            System.out.println("Choose from the following operations that you want to perform.");
            System.out.println("1 to view your balance.");
            System.out.println("2 to credit your account.");
            System.out.println("3 to withdraw amount.");
            System.out.println("4 to transfer money.");
            System.out.println("5 to view your transactions.");
            System.out.println("6 to view account information.");
            System.out.println("7 to change password.");
            System.out.println("8 to delete the account");
            System.out.println("Any other thing to quit.");

            float amount;
            String password;
            String currentPassword;

            switch (scanner.nextLine().charAt(0)) {
                case '1':
                    System.out.println("Dear customer, your account " + account.getAccountNumber() + " have the balance of " + account.getBalance() + " USD.");
                    break;
                case '2':
                    System.out.println("Enter the amount to deposit :: ");
                    amount = Float.parseFloat(scanner.nextLine().strip());
                    this.creditBalance(amount);
//                    System.out.println("Your account has been credited with " + amount + " USD.");
                    break;
                case '3':
                    System.out.println("Enter the amount to withdraw :: ");
                    amount = Float.parseFloat(scanner.nextLine().strip());
                    this.debitBalance(Float.parseFloat(scanner.nextLine().strip()));
//                    System.out.println("Your account has been debited with " + amount + " USD.");
                    break;
                case '4':
                    System.out.println("Enter the destination account :: ");
                    int destAccNumber = Integer.parseInt(scanner.nextLine());
                    boolean existence = AccountDao.exists(destAccNumber);
                    if(existence) {
                        System.out.println("Enter the amount :: ");
                        amount = Float.parseFloat(scanner.nextLine());
                        this.debitBalance(amount);
                        Account destination = AccountDao.getAccount(destAccNumber);
                        destination.setBalance( destination.getBalance() + amount);
                        AccountDao.updateBalance(destination);
                        long txnHash = HashCode.generateHash(destAccNumber, amount, Mode.CREDIT);
                        TxnDao.insertTxnByFields(txnHash, destAccNumber, amount, Mode.CREDIT, new Timestamp(new Date().getTime()));
                        System.out.println("Dear customer, the amount has been successfully to the account " + destAccNumber);
                        break;
                    }
                case '5':
                    this.printTransactions();
                    break;
                case '6':
                    this.printAccountInfo();
                    break;
                case '7':
                    System.out.println("Enter your old password :: ");
                    currentPassword = scanner.nextLine().strip();
                    if(Objects.equals(AccountDao.readPassword(account.getAccountNumber()), currentPassword)) {
                        System.out.println("Enter your new password :: ");
                        password = scanner.nextLine().strip();
                        account.setPassword(password);
                        AccountDao.updatePassword(account);
                        System.out.println("Your password has been updated.");
                    }
                    break;
                case '8':
                    System.out.println("Enter your password :: ");
                    currentPassword = scanner.nextLine().strip();
                    if(Objects.equals(AccountDao.readPassword(account.getAccountNumber()), currentPassword)) {
                        AccountDao.deleteAccount(accountNumber);
                    }
                    System.out.println("Deleted account " + accountNumber);

                default:
                    logInState = false;
                    account = null;
                    System.out.println("Logged out.");
            }
        }
    }

    private void printAccountInfo() {
        System.out.println("Your account details :");
        System.out.println(account.toString());
    }

    private void creditBalance(float amount) throws SQLException, IOException, ClassNotFoundException {
        if (account.deposit(amount)) {
            AccountDao.updateBalance(account);
            long txnHash = HashCode.generateHash(account.getAccountNumber(), amount, Mode.CREDIT);
            Transaction transaction = Transaction.retrTxn(txnHash, account.getAccountNumber(), amount, Mode.CREDIT, new Timestamp(new Date().getTime()));
            TxnDao.insertTransaction(transaction);
        }
    }

    private void debitBalance(float amount) throws SQLException, IOException, ClassNotFoundException {
        if (account.withdraw(amount)) {
            AccountDao.updateBalance(account);
            long txnHash = HashCode.generateHash(account.getAccountNumber(), amount, Mode.DEBIT);
            Transaction transaction = Transaction.retrTxn(txnHash, account.getAccountNumber(), amount, Mode.DEBIT, new Timestamp(new Date().getTime()));
            TxnDao.insertTransaction(transaction);
        }
    }

    private void printTransactions() throws SQLException, IOException, ClassNotFoundException {
        int accountNumber = account.getAccountNumber();
        HashMap<Long, Transaction> transactionHashMap = TxnDao.getTransactionsByAccount(accountNumber);
        for (Transaction txn: transactionHashMap.values()) {
            System.out.println(txn.toString());
        }
    }
}