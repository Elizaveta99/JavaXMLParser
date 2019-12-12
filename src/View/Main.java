package View;

import Controller.Controller;
import Model.ColdSteelCollection;
import Model.ColdSteel;
import XML.ColdSteelCollectionDOMParser;
import XML.ColdSteelCollectionParser;
import XML.ColdSteelCollectionSAXParser;
import XML.ColdSteelCollectionStAXParser;

import java.util.List;
import java.util.Scanner;

/**
 * Main class which executes methods from Controller
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class Main {
    /**
     * The Main method
     * @param args command line parameters
     */
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Incorrect param, use with param:\n\t{xml document} {xsd schema}");
            System.exit(1);
        }
        System.out.println("Validate " + args[0] + " with " + args[1] + " schema.");
        if (!Controller.validate(args[0], args[1]))
        {
            System.out.println("Validation failed.");
            System.exit(1);
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Choose parser:\n" +
                "1 - DOM\n" +
                "2 - SAX\n" +
                "3 - StAX");

        ColdSteelCollectionParser parser = null;
        int choice;
        while (true){
            choice = in.nextInt();
            switch (choice){
                case 1:
                    parser = new ColdSteelCollectionDOMParser();
                    break;
                case 2:
                    parser = new ColdSteelCollectionSAXParser();
                    break;
                case 3:
                    parser = new ColdSteelCollectionStAXParser();
                    break;
                default:
                    System.out.println("Incorrect choice. Try again.");
                    break;
            }
            if (parser != null){
                break;
            }
        }
        ColdSteelCollection cl = null;
        try {
            cl = parser.parse(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("Use " + parser.getClass().getSimpleName());
        System.out.println(cl.getName());

        List<ColdSteel> lp = cl.getAllWeapons();
        System.out.println("All weapons:");
        System.out.println(Controller.getWeapons(lp));

        //cost, sort, byPrice, byLength

        lp = Controller.searchByPrice(cl, 200, 350);
        System.out.println("Weapons with price between 200 and 350: ");
        System.out.println(Controller.getWeapons(lp));

        lp = Controller.searchByLength(cl, 3, 5);
        System.out.println("Weapons with length of the title between 3 and 5: ");
        System.out.println(Controller.getWeapons(lp));

        Double cost = Controller.getCommonCost(cl);
        System.out.println("Common cost is: ");
        System.out.println(cost);

        lp = Controller.getSortedList(cl);
        System.out.println("Sorted by type weapons: ");
        System.out.println(Controller.getWeapons(lp));
    }

}