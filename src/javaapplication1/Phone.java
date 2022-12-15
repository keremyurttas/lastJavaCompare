/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Pclab02
 */
public class Phone extends Product {

    double fCam;
    double mainCam;
    int battery;

    public Phone(double fCam, double mainCam, int battery, String proccesor, double screen, double weight, double size, int storage, String brand, String model, int year, String operatingSystem, double price, int ram) {
        super(proccesor, screen, weight, size, storage, brand, model, year, operatingSystem, price, ram);
        this.fCam = fCam;
        this.mainCam = mainCam;
        this.battery = battery;
    }

    public double getfCam() {
        return fCam;
    }

    public void setfCam(double fCam) {
        this.fCam = fCam;
    }

    public double getMainCam() {
        return mainCam;
    }

    public void setMainCam(double mainCam) {
        this.mainCam = mainCam;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

}
