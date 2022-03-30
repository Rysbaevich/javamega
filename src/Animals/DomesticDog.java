package Animals;

public class DomesticDog extends Dog {
    private String type;

    public DomesticDog(String name, double weight, int energyLevel, String type) {
        super(name, weight, energyLevel);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String print() {
        return "Name: " + this.getName() + "\nWeight: " + this.getWeight() + "\nEnergy level: " + getEnergyLevel() +
                "\nType: " + getType() + "\n";
    }

    @Override
    public String sound() {
        return "Bark";
    }
}
