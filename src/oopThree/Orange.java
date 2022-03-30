package oopThree;

public class Orange extends Fruit {

    private boolean isContainBeans;

    public Orange(String color, boolean isLocal, boolean isContainBeans) {
        super(color, isLocal);
        this.isContainBeans = isContainBeans;
    }

    public boolean isContainBeans() {
        return isContainBeans;
    }

    public void setContainBeans(boolean containBeans) {
        isContainBeans = containBeans;
    }

    @Override
    public String howToEat() {
        return "Clean and eat";
    }

    @Override
    public String toString() {
        return "Orange{" +
                "color='" + color + '\'' +
                ", isLocal=" + isLocal +
                ", isContainBeans=" + isContainBeans +
                '}';
    }
}
