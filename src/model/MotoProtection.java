package model;

public class MotoProtection extends Equipment{


    private String typeProtection;
    private String size;

    public MotoProtection(double price, double weight, String nameOfEquipment, String typeProtection, String size) {
        super(price, weight, nameOfEquipment);
        this.typeProtection = typeProtection;
        this.size = size;
    }

    public String getTypeProtection() {
        return typeProtection;
    }

    public void setTypeProtection(String typeProtection) {
        this.typeProtection = typeProtection;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "MotoProtection{" +
                "typeProtection='" + typeProtection + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
