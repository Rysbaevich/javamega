package Beecrowd;

import java.util.Scanner;

public class ArrayReplacementI_1172 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = scan.nextInt();
            if ((array[i] <= 0)) {
                array[i] = 1;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("X[%d] = %d\n", i,  array[i]);
        }
    }
}
