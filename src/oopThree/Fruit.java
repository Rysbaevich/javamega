package oopThree;

public abstract class Fruit implements Edible {
    protected String color;
    protected boolean isLocal;

    public Fruit(String color, boolean isLocal) {
        this.color = color;
        this.isLocal = isLocal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }


}
