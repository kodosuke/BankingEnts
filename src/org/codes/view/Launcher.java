package org.codes.view;

import org.codes.dao.AccountDao;
import org.codes.utils.Type;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class Launcher {

    static int accountNumTracker = 1001;

    static {
        try {
            accountNumTracker = AccountDao.readLastAccountNumber();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start() throws SQLException, IOException, ClassNotFoundException {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        do {
//            System.out.println("Welcome to Ajay's Bank.");
            System.out.println("Choose from the following actions :");
            System.out.println("1 to register a new account.");
            System.out.println("2 to log into your account.");
            System.out.println("Anything to quit. \n\n ::");
            switch (scanner.nextLine().charAt(0)) {
                case '1':
                    registerNewAccount(scanner);
                    break;
                case '2':
                    new LogIn().loginToAccount(scanner);
                    break;
                default:
                    quit = true;
            }

        } while ( !quit);
        System.out.println("Thank you and best wishes.");
    }


    private static void registerNewAccount(Scanner scanner) throws SQLException, IOException, ClassNotFoundException {
//        System.out.println(accountNumTracker);
        System.out.println("Let's create a new account for you.");
        System.out.println("Enter your full name :: ");
        String customerName = scanner.nextLine().strip();
        System.out.println("Create a password :: ");
        String password = scanner.nextLine().strip();
        System.out.println("Choose your account \n\t1 for SAVINGS\n\tAnything for CURRENT\n\t:: ");
        Type accountType;
        if(scanner.nextLine().charAt(0) =='1') {
            accountType = Type.SAVINGS;
        } else {
            accountType = Type.CURRENT;
        }
        int accountNumber = ++accountNumTracker;

        AccountDao.registerAccount(accountNumber, customerName, accountType, 0, password, new Timestamp(new Date().getTime()));

        System.out.println("Thanks for your cooperation. Your account with accountNumber " + accountNumber + " and balance 0 USD is created successfully. Login to credit your account.");

    }
}
