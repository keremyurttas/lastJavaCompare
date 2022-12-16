package javaapplication1;

import java.io.Serializable;

public class Laptop extends Product implements Serializable {

    String GPU;
    int id;  

    public Laptop(String GPU, int id, String proccesor, double screen, double weight, double size, int storage, String brand, String model, int year, String operatingSystem, int price, int ram) {
        super(proccesor, screen, weight, size, storage, brand, model, year, operatingSystem, price, ram);
        this.GPU = GPU;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

}
