package Beecrowd;

import java.util.Scanner;

public class ArrayFillI_1173 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] array = new int[10];
        array[0] = scan.nextInt();
        for (int i = 1; i < array.length; i++) {
            array[i] = array[i - 1] * 2;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("N[%d] = %d\n", i,  array[i]);
        }
    }
}
