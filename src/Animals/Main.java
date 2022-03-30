package Animals;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = new Animal[4];
        animals[0] = new Wolf("Artemis", 18.10, 100, "Zeus");
        animals[1] = new DomesticDog("Sherlock", 14.23, 234, "Holmes" );
        animals[2] = new Leopard("Cheetah", 12.4, "Black", 323);
        animals[3] = new HouseCat("Luna", 2.3, "Good", "Luci");

        /*System.out.println(wolf.print());
        System.out.println();
        System.out.println(domesticDog.print());
        System.out.println();
        System.out.println(leopard.print());
        System.out.println();
        System.out.println(houseCat.print());*/

        for (int i = 0; i < animals.length; i++) {
            if (animals[i] instanceof Wolf) {


            }
        }
    }
}
