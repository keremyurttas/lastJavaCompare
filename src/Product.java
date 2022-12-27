
import java.io.Serializable;

public class Product implements Serializable {

    String proccesor;
    double screen;
    double weight;
    String storage;
    String brand;
    String model;
    int year;
    String operatingSystem;
    int price;
    int ram;

    public Product(String proccesor, double screen, double weight, String storage, String brand, String model, int year, String operatingSystem, int price, int ram) {
        this.proccesor = proccesor;
        this.screen = screen;
        this.weight = weight;

        this.storage = storage;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.operatingSystem = operatingSystem;
        this.price = price;
        this.ram = ram;
    }

    public Product(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Product(String model, int price) {
        this.model = model;
        this.price = price;
    }


public String getProccesor() {
        return proccesor;
    }

    public void setProccesor(String proccesor) {
        this.proccesor = proccesor;
    }

    public double getScreen() {
        return screen;
    }

    public void setScreen(double screen) {
        this.screen = screen;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
