package oopThree;

public class Apple extends Fruit {

    private String type;

    public Apple(String color, boolean isLocal, String type) {
        super(color, isLocal);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String howToEat() {
        return "Juice";
    }

    @Override
    public String toString() {
        return "Apple{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", isLocal=" + isLocal +
                '}';
    }
}
