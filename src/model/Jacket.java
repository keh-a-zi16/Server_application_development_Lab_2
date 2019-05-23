package model;

public class Jacket extends Equipment {


    private String material;
    private String size;

    public Jacket(double price, double weight, String nameOfEquipment, String material, String size) {
        super(price, weight, nameOfEquipment);
        this.material = material;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Куртка{" +
                "материал='" + material + '\'' +
                ", размер='" + size + '\'' +
                '}';
    }
}
