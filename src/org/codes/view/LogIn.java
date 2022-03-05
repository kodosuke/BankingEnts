package org.codes.view;

import org.codes.dao.AccountDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class LogIn {
    static boolean logInState = false;
    public static void loginToAccount(Scanner scanner) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Please enter your credentials to log in.");
        System.out.println("Enter your account number :: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your password :: ");
        String password = scanner.nextLine().strip();
        if(AccountDao.readPassword(accountNumber).equals(password)) {
            logInState = true;
            System.out.println("Logged into your account, " + accountNumber);
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    public static void performUser() {
        while (logInState) {
            System.out.println("Choose from the following operations that you want to perform.");
            System.out.println("1 to view your balance.");
            System.out.println("2 to credit your account.");
            System.out.println("3 to withdraw amount.");
            System.out.println("4 to transfer money.");
            System.out.println("5 to view your transactions.");
            System.out.println("6 to view account information.");
            System.out.println("7 to change password.");
            System.out.println("Any other thing to quit.");
        }
    }
}
