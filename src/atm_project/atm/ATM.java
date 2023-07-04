package atm_project.atm;

import java.util.Scanner;

public abstract class ATM implements Machine, MoneyFunctions {

    private final double withdrawLimit;

    private boolean powerOn;


    protected ATM(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public boolean isPowerOn() {
        return powerOn;
    }



   public abstract void interfaceMenu(Scanner input);
    public abstract void login(String username, int pin);
    public abstract void logout();
}
