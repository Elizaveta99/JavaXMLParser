package Model;

import java.util.Comparator;

/**
 * Class-comparator for ColdSteel objects
 */
public class ColdSteelComparator implements Comparator<ColdSteel>
{
    /**
     * Method matches type with certain number for sorting method
     * @param type - type of cold steel
     * @return - number for type
     */
    private int getNumber(String type)
    {
        if(type.equals("Piercing"))
            return 1;
        if(type.equals("Chopping"))
            return 2;
        if(type.equals("Stabbing"))
            return 3;
        if(type.equals("ShockFragmenting"))
            return 4;
        return 0;
    }

    @Override
    public int compare(ColdSteel o1, ColdSteel o2)
    {
        return getNumber(o1.getType()) - getNumber(o2.getType());
    }
}
