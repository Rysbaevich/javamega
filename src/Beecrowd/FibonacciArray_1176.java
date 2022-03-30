package Beecrowd;

import java.util.Scanner;

public class FibonacciArray_1176 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        long[] array = new long[61];
        array[0] = 0;
        array[1] = 1;

        for (int j = 2; j < array.length; j++) {
            array[j] = array[j - 1] + array[j - 2];
        }

        for (; i > 0; i--) {
            int number = scan.nextInt();
            System.out.printf("Fib (%d) = %d\n", number, array[number]);
        }
    }
}
