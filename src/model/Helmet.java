package model;

public class Helmet extends Equipment{

    private String color;
    private boolean isOpen;

    public Helmet(double price, double weight, String nameOfEquipment, String color, boolean isOpen) {
        super(price, weight, nameOfEquipment);
        this.color = color;
        this.isOpen = isOpen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "Helmet{" +
                "color='" + color + '\'' +
                ", isOpen=" + isOpen +
                '}';
    }
}
