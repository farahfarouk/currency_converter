import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class CurrencyExchange {

    public HashMap<String, Float> ReadFile(File FilePath, String s) {
        HashMap<String, Float> Hash = new HashMap<>();
        try {

            Scanner in = new Scanner(new FileReader(FilePath));
            while (in.hasNextLine()) {
                String[] r = in.nextLine().split(s);

                    String key = String.valueOf(r[0].trim());
                    Float value = Float.valueOf(r[2]);
                    Hash.put(key, value);

            }
        } catch (Exception e) {
            e.getMessage();
        }
    return Hash;
}


    public static float Exchange(HashMap<String,Float> Hash, String key, String key2, Float amount){
        float valueReturn = 0;
        try {
            if (Hash.containsKey(key) && Hash.containsKey(key2)) {
                float sourceC = Hash.get(key);
                float destinationC = Hash.get(key2);
                valueReturn = (amount / sourceC) * destinationC;
            } else if (!Hash.containsKey(key) || !Hash.containsKey(key2)) {
                throw new CurrencyExchangeException();
            }
        }
        catch (CurrencyExchangeException e) {
            e.printStackTrace();
        }

        return valueReturn;

    }


    public static void main(String args[]){

        CurrencyExchange ce =  new CurrencyExchange();
        HashMap<String, Float> CurrencyTable1 = new HashMap<>();

        File file = new File("forex.csv");
        try{

            Scanner in = new Scanner(new FileReader(file));
        }catch (Exception e){
            e.getMessage();
        }

            CurrencyTable1 = ce.ReadFile(file, ",");

        System.out.println(CurrencyTable1);


        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the amount to exchange:");
        Float AmountEntry = Float.parseFloat(scan.nextLine());

        System.out.println("\nPleaseenter the source currency to exchange:");
        String key = scan.nextLine();
        key = key.toUpperCase();

        System.out.println("\nPlease enter the destination currency:");
        String key2 = scan.nextLine();
        key2 = key2.toUpperCase();

        Float AmountReturn = Exchange(CurrencyTable1,key,key2,AmountEntry);

        System.out.println("The exchange rate between the source and the destination currencies is: "
                +AmountEntry+" "+key+" = "+AmountReturn+" "+key2);


    }
}
