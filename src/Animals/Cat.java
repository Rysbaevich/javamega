package Animals;

public abstract class Cat extends Animal {
    protected String mood;

    public Cat() {
    }

    public Cat(String name, double weight, String mood) {
        super(name, weight);
        this.mood = mood;
    }


    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
