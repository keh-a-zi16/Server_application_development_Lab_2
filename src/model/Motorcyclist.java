package model;

import java.io.Serializable;
import java.util.List;

public class Motorcyclist implements Serializable {

    private String firstNameOfMotorcyclist;
    private String secondNameOfMotorcyclist;
    private List<Equipment> equipmentList;

    public Motorcyclist(String firstNameOfMotorcyclist, String secondNameOfMotorcyclist, List<Equipment> equipmentList) {
        this.firstNameOfMotorcyclist = firstNameOfMotorcyclist;
        this.secondNameOfMotorcyclist = secondNameOfMotorcyclist;
        this.equipmentList = equipmentList;
    }

    public String getFirstNameOfMotorcyclist() {
        return firstNameOfMotorcyclist;
    }


    public String getSecondNameOfMotorcyclist() {
        return secondNameOfMotorcyclist;
    }


    public void addEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;

    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    @Override
    public String toString() {
        return "Motorcyclist{" +
                "firstNameOfMotorcyclist='" + firstNameOfMotorcyclist + '\'' +
                ", secondNameOfMotorcyclist='" + secondNameOfMotorcyclist + '\'' +
                ", equipmentList=" + equipmentList +
                '}';
    }
}
