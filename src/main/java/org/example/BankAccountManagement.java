package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BankAccountManagement implements Serializable {
    private Map<String, Double> accounts = new HashMap<>();
    public void Deserialize() {
        try{
            //Creating an input stream to reconstruct the object from serialised data
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("accountManagement.ser"));
            BankAccountManagement accountManagement=(BankAccountManagement)in.readObject();
            in.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void Serialize() {
        try {
            // Creating the object

            // Creating output stream and writing the serialised object
            FileOutputStream outfile = new FileOutputStream("accountManagement.ser");
            ObjectOutputStream outstream = new ObjectOutputStream(outfile);
            outstream.writeObject(this);
            outstream.flush();
            // closing the stream
            outstream.close();
            System.out.println("Serialized data saved to accountManagement.ser");
        } catch (Exception e) {
            System.out.println(e);
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