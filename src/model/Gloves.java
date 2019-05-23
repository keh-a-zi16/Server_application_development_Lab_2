package model;

public class Gloves extends Equipment{

    private boolean isClipped;
    private String color;

    public Gloves(double price, double weight, String nameOfEquipment, boolean isClipped, String color) {
        super(price, weight, nameOfEquipment);
        this.isClipped = isClipped;
        this.color = color;
    }

    public boolean isClipped() {
        return isClipped;
    }

    public void setClipped(boolean clipped) {
        isClipped = clipped;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Gloves{" +
                "isClipped=" + isClipped +
                ", color='" + color + '\'' +
                '}';
    }
}
