package Animals;

public class HouseCat extends Cat {
    private String type;

    public HouseCat(String name, double weight, String mood, String type) {
        super(name, weight, mood);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String print() {
        return "Name: " + this.getName() + "\nWeight: " + this.getWeight() + "\nMood: " + getMood() +
                "\nType: " + getType() + "\n";
    }


    @Override
    public String sound() {
        return "Miaw";
    }
}
