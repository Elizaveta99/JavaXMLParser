package Controller;

import Model.ColdSteelCollection;
import Model.ColdSteel;
import Model.ColdSteelComparator;
import XML.ValidatorXML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class to demonstrate functionality
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class Controller {

    /**
     * Validates XML file with given schema
     * @param file XML file
     * @param schema XSD file
     * @return true if validation succeed, otherwise false
     */
    public static boolean validate(String file, String schema){
        boolean result = ValidatorXML.validate(file, schema);
        if (!result){
            System.out.println("Validation failed");
        }
        else {
            System.out.println("Validation succeed");
        }
        return result;
    }


    public static String numberOfWeapons(ColdSteelCollection cl){
        return new String("Number of weapons: " + cl.getAllWeapons().size());
    }

    /**
     * Method that finds common cost
     * @param collection - our list
     * @return - common cost
     */
    public static double getCommonCost(ColdSteelCollection collection)
    {
        List<ColdSteel> weapons = collection.getAllWeapons();
        double cost = 0;
        for (ColdSteel weapon: weapons)
        {
            cost += weapon.getPrice();
        }
        return  cost;
    }

    /**
     * Sorts collection of weapons by type
     * @param collection - sorted weapons
     */
    public static List<ColdSteel> getSortedList(ColdSteelCollection collection)
    {
        List<ColdSteel> weapons = collection.getAllWeapons();
        Collections.sort(weapons, new ColdSteelComparator());
        return weapons;
    }

    public static List<ColdSteel> searchByLength(ColdSteelCollection collection, double firstParam, double secondParam)
    {
        List<ColdSteel> weapons = collection.getAllWeapons();
        List<ColdSteel> ans = new ArrayList<>();
        for (ColdSteel weapon: weapons)
        {
            if (weapon.getTitle().length() >= firstParam && weapon.getTitle().length() <= secondParam)
                ans.add(weapon);
        }
        return ans;
    }

    public static List<ColdSteel> searchByPrice(ColdSteelCollection collection, double firstParam, double secondParam)
    {
        List<ColdSteel> weapons = collection.getAllWeapons();
        List<ColdSteel> ans = new ArrayList<>();
        for (ColdSteel weapon: weapons)
        {
            if (weapon.getPrice() >= firstParam && weapon.getPrice() <= secondParam)
                ans.add(weapon);
        }
        return ans;
    }


    public static String getWeapons(List<ColdSteel> l){
        String ls = "";
        for(ColdSteel p: l)
            ls += "\n" + p;
        return ls + "\n***";
    }

}