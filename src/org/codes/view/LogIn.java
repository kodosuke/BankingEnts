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
}
