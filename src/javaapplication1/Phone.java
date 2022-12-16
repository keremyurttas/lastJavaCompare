package javaapplication1;

import java.io.Serializable;

public class Phone extends Product implements Serializable {

    double fCam;
    double mainCam;
    int battery;
    int id;

    public Phone(double fCam, double mainCam, int battery, int id, String proccesor, double screen, double weight, double size, int storage, String brand, String model, int year, String operatingSystem, int price, int ram) {
        super(proccesor, screen, weight, size, storage, brand, model, year, operatingSystem, price, ram);
        this.fCam = fCam;
        this.mainCam = mainCam;
        this.battery = battery;
        this.id = id;
    }

    public int getId() {
        return id;
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
