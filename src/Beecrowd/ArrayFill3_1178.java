package Beecrowd;

import java.util.Scanner;

public class ArrayFill3_1178 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double number = scan.nextDouble();

        double[] array = new double[100];

        for (int i = 0; i < 100; i++) {
            array[i] = number;
            number /= 2;
            System.out.printf("N[%d] = %.4f\n", i, array[i]);
        }
    }
}
