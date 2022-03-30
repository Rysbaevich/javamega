package coffee;

import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static int water;
    public static int milk;
    public static int coffeeBeans;
    public static int totalSum;
    public static int money;

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        while (true) {
            try {
                System.out.print("Operation: ");
                Operation operation = Operation.valueOf(scan.nextLine().trim().toUpperCase());


                switch (operation) {
                    case FILL:
                        fill();
                        break;
                    case ORDER:
                        order();
                        break;
                    case PAY:
                        pay();
                        break;
                    case CHECK:
                        checkReserves();
                        break;
                }
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static void checkReserves() {
        System.out.printf("Milk reserve: %d\nYou need fill %d ml.\n\n", Main.milk, 5000 - Main.milk);
        System.out.printf("Water reserve: %d\nYou need fill %d ml.\n\n", Main.milk, 10000 - Main.milk);
        System.out.printf("Coffee beans reserve: %d\nYou need fill %d ml.\n\n", Main.milk, 1000 - Main.milk);
    }

    public static void fill() {
        System.out.printf("Milk remained: %d ml. Limit: 5000 ml. Fill: ", Main.milk);
        int milk = scan.nextInt();
        Main.milk += Math.min(milk, 5000 - Main.milk);
        System.out.println("Filled " + Main.milk + " ml from " + milk + " ml.");

        System.out.printf("Water remained: %d ml. Limit: 10 000 ml. Fill: ", Main.water);
        int water = scan.nextInt();
        Main.water += Math.min(water, 10_000 - Main.water);
        System.out.println("Filled " + Main.water + " ml from " + water + " ml.");

        System.out.printf("Coffee beans remained: %d ml. Limit: 1000 ml. Fill: ", Main.coffeeBeans);
        int coffeeBeans = scan.nextInt();
        scan.nextLine();
        Main.coffeeBeans += Math.min(coffeeBeans, 1000 - Main.coffeeBeans);
        System.out.println("Filled " + Main.coffeeBeans + " ml from " + coffeeBeans + " ml.");
    }

    public static void order() {
        System.out.printf("Available types:\n1) %s 170 som\n2) %s 180 som\n3) %s 190 som\n4) %s 180 som\n",
                CoffeeType.AMERICANO,
                CoffeeType.CAPPUCCINO,
                CoffeeType.LATTE,
                CoffeeType.ESPRESSO);

        System.out.print("Choose coffee: ");

        CoffeeType coffeeType = CoffeeType.valueOf(scan.nextLine().trim().toUpperCase());

        switch (coffeeType) {
            case AMERICANO:
                check(220, 0, 9);
                totalSum += 170;
                water -= 220;
                coffeeBeans -= 9;
                break;
            case CAPPUCCINO:
                check(160, 60, 9);
                totalSum += 180;
                water -= 160;
                coffeeBeans -= 9;
                milk -= 60;
                break;
            case LATTE:
                check(140, 80, 9);
                totalSum += 190;
                water -= 140;
                coffeeBeans -= 9;
                milk -= 80;
                break;
            case ESPRESSO:
                check(50, 0, 9);
                totalSum += 180;
                water -= 50;
                coffeeBeans -= 9;
                break;
        }

        System.out.printf("To pay: %d\n", totalSum);
    }

    public static void pay() {
        int change;

        do {
            System.out.println("Total sum: " + totalSum + " som");
            System.out.print("Payed ");

            money += scan.nextInt();
            scan.nextLine();

            change = money - totalSum;
            if (change < 0) {
                System.out.printf("Not enough money, add %d som\n", totalSum - money);
            }

        } while (change < 0);

        System.out.println("Change");

        System.out.printf("1000 som: %d\n", change / 1000);
        change %= 1000;
        System.out.printf("500 som: %d\n", change / 500);
        change %= 500;
        System.out.printf("200 som: %d\n", change / 200);
        change %= 200;
        System.out.printf("100 som: %d\n", change / 100);
        change %= 100;
        System.out.printf("50 som: %d\n", change / 50);
        change %= 50;
        System.out.printf("20 som: %d\n", change / 20);
        change %= 20;
        System.out.printf("10 som: %d\n", change / 10);
        change %= 10;
        System.out.printf("5 som: %d\n", change / 5);
        change %= 5;
        System.out.printf("3 som: %d\n", change / 3);
        change %= 3;
        System.out.printf("1 som: %d\n", change);
        money = 0;
        totalSum = 0;
    }

    public static void check(int water, int milk, int coffeeBeans) {
        if (water > Main.water) {
            throw new RuntimeException("Not enough water");
        }
        if (milk > Main.milk) {
            throw new RuntimeException("Not enough milk");
        }
        if (coffeeBeans > Main.coffeeBeans) {
            throw new RuntimeException("Not enough Coffee beans");
        }
    }
}
