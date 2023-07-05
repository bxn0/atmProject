package atm_project.main;

import atm_project.atm.ATM;
import atm_project.atm.ChaseBranch;
import atm_project.atm.ChaseExternal;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ATM atm = null;


        if (Data.get("branch").equals("chase external")) {

            atm = new ChaseExternal();

        } else if(Data.get("branch").equals("chase branch") ){ atm = new ChaseBranch();
        }

        // atm.interfaceMenu(input); need to login first

        // System.out.println(Data.allAccounts);

        System.out.println("Enter your username and pin>>");


        //atm.login("annabananna", 3341);
        atm.login(input.next(), input.nextInt());
        atm.interfaceMenu(input);


        atm.turnOff();


    }
}