
import java.io.Serializable;

public class Laptop extends Product implements Serializable {

    String GPU;
    int id;
    String proccesor;
    int storage;

    public String getProccesor() {
        return proccesor;
    }

    public void setProccesor(String proccesor) {
        this.proccesor = proccesor;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
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
    String operatingSystem;
    int ram;

    public Laptop(int id, String brand, String model, String GPU, String proccesor, int ram, String storage, double screen, double weight, int year, String operatingSystem, int price) {
        super(proccesor, screen, weight, storage, brand, model, year, operatingSystem, price, ram);
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
