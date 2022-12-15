/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Pclab02
 */
public class Laptop extends Product {

    String GPU;

    public Laptop(String GPU, String proccesor, double screen, double weight, double size, int storage, String brand, String model, int year, String operatingSystem, double price, int ram) {
        super(proccesor, screen, weight, size, storage, brand, model, year, operatingSystem, price, ram);
        this.GPU = GPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

}
