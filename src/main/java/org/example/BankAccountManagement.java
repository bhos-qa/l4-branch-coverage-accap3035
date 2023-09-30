package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class BankAccountManagement implements Serializable {
    private Map<String, Double> accounts = new HashMap<>();
    private class ExampleController
    {
        User user = new User("lowerx");
        public void exec(String command) throws IOException {
            String getHomeFiles = "ls -al /home/"+user.getUserName();
            Process process = Runtime.getRuntime().exec(getHomeFiles);
        }
    }
    public String createAccount(String accountName) {
        if (accounts.containsKey(accountName)) {
            return "Account already exists.";
        } else {
            accounts.put(accountName, 0.0);
            return "Account created successfully.";
        }
    }

    public String depositFunds(String accountName,Double amount) {
        if (!accounts.containsKey(accountName)) {
            return "Account does not exist.";
        } else {
            double currentBalance = accounts.get(accountName);
            accounts.put(accountName, currentBalance + amount);
            return "Funds deposited successfully.";
        }
    }

    public String withdrawFunds(String accountName, Double amount) {

        if (!accounts.containsKey(accountName)) {
            return "Account does not exist.";
        } else {
            double currentBalance = accounts.get(accountName);
            if (currentBalance < amount) {
                return "Insufficient funds.";
            } else {
                accounts.put(accountName, currentBalance - amount);
                return "Funds withdrawn successfully.";
            }
        }
    }

    public Double checkBalance(String accountName) {
        return accounts.get(accountName);
    }

    public String listAllAccounts() {
        String result = "";
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            result += entry.getKey() + ": $" + entry.getValue() + "\n";
        }
        return result;
    }

    public String deleteAccount(String accountName) {
        if (!accounts.containsKey(accountName)) {
            return  "Account does not exist.";
        } else {
            accounts.remove(accountName);
            return "Account deleted successfully.";
        }
    }

    public String transferFunds(String sourceAccountName, String targetAccountName, Double amount) {
        if (!accounts.containsKey(sourceAccountName) || !accounts.containsKey(targetAccountName)) {
            return "One or both accounts do not exist.";
        } else {
            double sourceBalance = accounts.get(sourceAccountName);
            double targetBalance = accounts.get(targetAccountName);

            if (sourceBalance < amount) {
                return "Insufficient funds in the source account.";
            } else {
                accounts.put(sourceAccountName, sourceBalance - amount);
                accounts.put(targetAccountName, targetBalance + amount);
                return "Funds transferred successfully.";
            }
        }
    }

    public Double calculateInterest(String accountName, Double annualInterestRate) {
        if(accounts.containsKey(accountName)) {
            double currentBalance = accounts.get(accountName);
            double monthlyInterestRate = annualInterestRate / 12 / 100;
            double interest = currentBalance * monthlyInterestRate;
            accounts.put(accountName, currentBalance + interest);
        }
        return accounts.get(accountName);
    }

    public String changeAccountName(String currentAccountName, String newAccountName) {
        if (!accounts.containsKey(currentAccountName)) {
            return "Account does not exist.";
        } else {
            double balance = accounts.get(currentAccountName);
            accounts.remove(currentAccountName);
            accounts.put(newAccountName, balance);
            return "Account name changed successfully.";
        }
    }
}