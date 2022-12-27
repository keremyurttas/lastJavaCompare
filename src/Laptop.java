
import java.io.Serializable;

public class Laptop extends Product implements Serializable {

    String GPU;
    int id;

    public Laptop(int id, String brand, String model, String GPU, String proccesor, int ram, String storage, double screen, double weight, int year, String operatingSystem, int price) {
        super(proccesor, screen, weight, storage, brand, model, year, operatingSystem, price, ram);
        this.GPU = GPU;
        this.id = id;
    }

    public Laptop(int id, String model, int price) {
        super(model, price);
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
