package Animals;

public class Wolf extends Dog {
    private String packLeaderName;

    public Wolf(String name, double weight, int energyLevel, String packLeaderName) {
        super(name, weight, energyLevel);
        this.packLeaderName = packLeaderName;
    }

    public Wolf() {
    }

    @Override
    public String sound() {
        return "Auuu";
    }

    public String getPackLeaderName() {
        return packLeaderName;
    }

    public void setPackLeaderName(String packLeaderName) {
        this.packLeaderName = packLeaderName;
    }

    public String print() {
        return "Name: " + this.getName() + "\nWeight: " + this.getWeight() + "\nEnergy level: " + getEnergyLevel() +
                "\nPack leader name: " + getPackLeaderName() + "\n";
    }
}
