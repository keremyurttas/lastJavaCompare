
import java.io.Serializable;

public class Laptop extends Product implements Serializable {

    int id, GPUscore;
    String GPU;

    public Laptop(int id, String brand, String model, String GPU, String proccesor, int ram, String storage, double screen, double weight, int year, String operatingSystem, int price, int GPUscore, String img) {
        super(proccesor, screen, weight, storage, brand, model, year, operatingSystem, price, ram, img);
        this.GPU = GPU;
        this.id = id;
        this.GPUscore = GPUscore;
    }

    public Laptop() {
        super();
        this.GPU = null;
        this.id = 0;
        this.GPUscore = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public int getGPUscore() {
        return GPUscore;
    }

    public void setGPUscore(int GPUscore) {
        this.GPUscore = GPUscore;
    }
}
