

public class Laptop extends Product{

    int id, GPUScore;
    String GPU;

    public Laptop(int id, String brand, String model, String GPU, String proccesor, int ram, int storage, double screen, double weight, int year, String operatingSystem, int price, int GPUscore, String img) {
        super(proccesor, screen, weight, storage, brand, model, year, operatingSystem, price, ram, img);
        this.GPU = GPU;
        this.id = id;
        this.GPUScore = GPUscore;
    }

    public Laptop() {
        super();
        this.GPU = null;
        this.id = 0;
        this.GPUScore = 0;

    }

    public void test() {
        System.out.println("test");
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

    public int getGPUScore() {
        return GPUScore;
    }

    public void setGPUScore(int GPUscore) {
        this.GPUScore = GPUscore;
    }
}
