package model;

import java.io.Serializable;

public class Equipment implements Serializable{

    private double price;
    private double weight;
    private String nameOfEquipment;

    public Equipment(double price, double weight, String nameOfEquipment) {
        this.price = price;
        this.weight = weight;
        this.nameOfEquipment = nameOfEquipment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNameOfEquipment() {
        return nameOfEquipment;
    }

    public void setNameOfEquipment(String nameOfEquipment) {
        this.nameOfEquipment = nameOfEquipment;
    }

    @Override
    public String toString() {
        return "экирировка{" +
                "цена=" + price +
                ", вес=" + weight +
                ", названиеЭкипировки='" + nameOfEquipment + '\'' +
                '}';
    }
}
