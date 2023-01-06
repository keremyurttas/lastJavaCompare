
import java.io.Serializable;

public class Product implements Serializable {

    double screen, weight;
    String brand, model, img, processor, storage, operatingSystem;
    int year, price, ram;

    public Product(String processor, double screen, double weight, String storage, String brand, String model, int year, String operatingSystem, int price, int ram, String img) {

        this.screen = screen;
        this.weight = weight;
        this.img = img;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.processor = processor;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.ram = ram;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    } 
}
