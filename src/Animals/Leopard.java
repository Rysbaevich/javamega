package Animals;

public class Leopard extends Cat {
    private int numberOfSpots;

    public Leopard() {
    }

    @Override
    public String sound() {
        return "Rrr";
    }

    public Leopard(String name, double weight, String mood, int numberOfSpots) {
        super(name, weight, mood);
        this.numberOfSpots = numberOfSpots;
    }

    public int getNumberOfSpots() {
        return numberOfSpots;
    }

    public void setNumberOfSpots(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }

    public String print() {
        return "Name: " + this.getName() + "\nWeight: " + this.getWeight() + "\nMood: " + getMood() +
                "\nNumber of spots: " + getNumberOfSpots() + "\n";
    }
}
