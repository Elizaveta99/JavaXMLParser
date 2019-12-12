package Model;

import java.util.Objects;

/**
 * Base class for factory
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public class ColdSteel {
    /**
     * Fields - type, title and price of cold steel
     */
    private String type;
    private String title;
    private  double price;

    /**
     * Describe all fields in ColdSteel class
     */
    public enum Fields {
        TYPE("type"),
        TITLE("title"),
        PRICE("price");
        private String name;
        Fields(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * Constructor with only mandatory parameters
     * @param type - type
     * @param title - title
     * @param price - price
     */
    public ColdSteel(String type, String title, double price)
    {
        this.type = type;
        this.title = title;
        this.price = price;
    }
    public ColdSteel() {}


    /**
     * Returns type of cold steel
     * @return type
     */
    public String getType() {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColdSteel)) return false;
        ColdSteel coldSteel = (ColdSteel) o;
        return Double.compare(coldSteel.getPrice(), getPrice()) == 0 &&
                Objects.equals(getType(), coldSteel.getType()) &&
                Objects.equals(getTitle(), coldSteel.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getTitle(), getPrice());
    }

    @Override
    public String toString() {
        return "ColdSteel: " +
                "type = " + type +
                ", title = " + title +
                ", price = " + price;
    }
}

