package oopThree;

public class Chicken extends Animal implements Edible {

    private double weight;

    public Chicken(String name, String color, double weight) {
        super(name, color);
        this.weight = weight;
    }

    @Override
    public String howToEat() {
        return "KFC";
    }

    @Override
    public String sound() {
        return "Kukareku";
    }

    @Override
    public String toString() {
        return "Chicken{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
