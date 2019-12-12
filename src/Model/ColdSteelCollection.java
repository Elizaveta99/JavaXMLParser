package Model;

import Model.ColdSteel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Model.ColdSteelCollection witch weapon
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class ColdSteelCollection {
    /**
     * Returns name of Collection
     * @return name
     */
    public String getName() {
        return name;
    }

    String name;
    List<ColdSteel> weapon;

    /**
     * Describe all fields in Collection class
     */
    public enum Fields {
        NAME("name"),
        WEAPONS("weapon");
        private String name;
        Fields(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * Add weapons to Collection
     * @param a weapon to add
     */
    public void addWeapon(ColdSteel a){
        this.weapon.add(a);
    }

    /**
     * Removes weapon from Collection
     * @param a weapon to remove
     */
    public void removeWeapon(ColdSteel a){
        this.weapon.remove(a);
    }

    /**
     * Create empty Collection
     */
    public ColdSteelCollection(){
        this.weapon = new ArrayList<>();
    }
    /**
     * Create empty Collection
     * @param name of Collection
     */
    public ColdSteelCollection(String name){
        this.name = name;
        this.weapon = new ArrayList<>();
    }

    /**
     * Creates collective with peoples
     * @param name of Collection
     * @param weapon lis of weapon
     */
    public ColdSteelCollection(String name, List<ColdSteel> weapon){
        this.name = name;
        this.weapon = weapon;
    }

    /**
     * Returns all weapons of Collection
     * @return list of peoples
     */
    public List<ColdSteel> getAllWeapons(){
        return this.weapon;
    }

    /**
     * Set name to Collection
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
}
