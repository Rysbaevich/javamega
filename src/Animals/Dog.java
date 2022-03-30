package Animals;

public abstract class Dog extends Animal {
    protected int energyLevel;

    public Dog() {

    }

    public Dog(String name, double weight, int energyLevel) {
        super(name, weight);
        this.energyLevel = energyLevel;
    }


    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }
}
