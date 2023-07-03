package atm_project.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Data { // can be called as utility class whatsoever

    private static Properties properties; // Reading from a properties file
    public static List<Account> allAccounts; //  List for all loaded accounts --- gives options over lists
    public static Account currentAccount; //   active logged in account
    public static long currentAvaiableAccNum = 214214420; //  used to assign unique account numbers for registration

        static {


            allAccounts = new ArrayList<>();

            properties = new Properties();
            try {
                properties.load(new FileReader("data.properties"));
            } catch (IOException e) {

                System.out.println("Data file not loaded");
                e.printStackTrace();
            }


        }

        public static  String get(String key){
            return properties.getProperty(key);

        }

        public static void loadAccounts(){


            //splitted with semi here
            String[] arr = get("alldata").split(";");

           // System.out.println(Arrays.toString(arr));

            for (String each: arr
                 ) {
                    //spliting again to get individual data from properties
                String[] forAccount = each.split(",");

                allAccounts.add(new Account(
                        forAccount[0],
                        Long.parseLong(forAccount[1]),
                        Double.parseDouble(forAccount[2]),
                        Integer.parseInt(forAccount[3])
                ));
            }

        }

    public static void main(String[] args) {
        loadAccounts();
        System.out.println(allAccounts);


    }


}
