package com.example.programm_8.Utility;

import com.example.programm_8.Data.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AuthorizationHelper {

    private Scanner scanner;

    public AuthorizationHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public User run() {
        try {
            if (askAboutSign()) {
                System.out.print("Enter your login: ");
                String login = scanner.nextLine();
                System.out.print("Enter your password: ");
                String rawPassword = scanner.nextLine();
                MessageDigest md = MessageDigest.getInstance("SHA-224");
                byte[] messageDigest = md.digest(rawPassword.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String password = no.toString(16);
                while (password.length() < 32) {
                    password = "0" + password;
                }
                return new User(login, password, true);
            } else {
                System.out.print("Enter your login: ");
                String login = scanner.nextLine();
                System.out.print("Enter your password: ");
                String rawPassword = scanner.nextLine();
                MessageDigest md = MessageDigest.getInstance("SHA-224");
                byte[] messageDigest = md.digest(rawPassword.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String password = no.toString(16);
                while (password.length() < 32) {
                    password = "0" + password;
                }
                return new User(login, password, false);
            }
        } catch (NoSuchAlgorithmException exception) {
            System.out.println("Authorization declined.");
        }
        return null;
    }

    private boolean askAboutSign() {
        while (true) {
            System.out.print("Please enter what sign you want? (in/up): ");
            String answer = scanner.nextLine();
            if (answer.equals("up")) {
                return true;
            } else if (answer.equals("in")) {
                return false;
            }
            System.out.println("Your answer is not correct.");
        }
    }
}
