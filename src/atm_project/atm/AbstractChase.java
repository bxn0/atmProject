package atm_project.atm;

import atm_project.exceptions.*;
import atm_project.main.Account;
import atm_project.main.Data;

import java.util.Scanner;

public abstract class AbstractChase extends ATM{


    public AbstractChase(double withdrawLimit) {
        super(withdrawLimit);
        turnOn();
    }


    @Override
    public void turnOn() {
        for (int i = 0; i < 5; i++) {

            System.out.println("ATM booting up...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        setPowerOn(true);
        Data.loadAccounts();

     }

    @Override
    public void turnOff() {
        System.out.println("ATM shutting down");
        setPowerOn(false);
        Data.allAccounts.clear();
    }

    @Override
    public void login(String username, int pin) {
        for (Account each: Data.allAccounts
             ) {
        if (each.getUsername().equals(username)){

            if (each.getPin() == pin){
                Data.currentAccount = each;
                System.out.println("Logged in");
                break;
            } else {
                throw new InvalidPinEnxception();
            }

        }
        }

        throw new UserNotFoundException("Username not found");
    }

    @Override
    public void logout() {
        System.out.println("Logged out");
        Data.currentAccount = null;
    }

    @Override
    public double withdraw(double amount) {
        if (amount <= getWithdrawLimit()){
            if (amount <= Data.currentAccount.getBalance()){
                System.out.println("Withdrawing $ " + amount);

                Data.currentAccount.updateBalance(-amount);


            }else {
                throw new NotEnoughMoneyException("Withdraw failure");
            }

        }else {
            throw new WithdrawLimitException();
        }

        return Data.currentAccount.getBalance();
    }

    @Override
    public double checkBalance() {
        return Data.currentAccount.getBalance();
    }

    @Override
    public double deposit(double amount) {
        System.out.println("Adding $ "+ amount + " into " + Data.currentAccount.getBalance());
        return Data.currentAccount.getBalance();
    }

    @Override
    public boolean transfer(long accountNumber, double amount) {
        for (Account each: Data.allAccounts
             ) {
            if ((each.getAccountNumber() == accountNumber)){
                if (Data.currentAccount.getBalance() >=amount ){
                    System.out.println("Transfering $ "+ " to " + accountNumber);
            each.updateBalance(amount);
            Data.currentAccount.updateBalance(-amount);
            return true;
                } else { throw new NotEnoughMoneyException("Cannot transfer $ " + amount + ", not enough in balance");}

            }
        }

        throw new UserNotFoundException("Cannot Transfer to This Account");
    }

    @Override
    public void interfaceMenu(Scanner input) {
        if (!isPowerOn()) {
            throw  new PowerNotFoundException();
        }

        if (Data.currentAccount == null){
            System.out.println("Please login first");
            return;
        }

        System.out.println("1) Check Balance\n2)Deposit\n3)Withdraw\n4)Transfer\n5)Logout");

        switch (input.nextInt()){
            case 1:
                System.out.println(checkBalance());
                break;
            case 2:
                System.out.println("How much to deposit");
                deposit(input.nextDouble());
                break;
            case 3:
                System.out.println("How much to withdraw");
                withdraw(input.nextDouble());
                break;
            case 4:
                System.out.println("Which account to transfer and how much to transfer");
                transfer(input.nextLong(), input.nextDouble());
                break;
            case 5:
                logout();
            default:
                System.out.println("Not a valid option, Try again");
        }


    }
}




























