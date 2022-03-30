package oopThree;

public class Tiger extends Animal {
    private int blackLines;

    public Tiger(String name, String color, int blackLines) {
        super(name, color);
        this.blackLines = blackLines;
    }

    public int getBlackLines() {
        return blackLines;
    }

    public void setBlackLines(int blackLines) {
        this.blackLines = blackLines;
    }

    @Override
    public String sound() {
        return "Rrr";
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", blackLines=" + blackLines +
                '}';
    }
}
