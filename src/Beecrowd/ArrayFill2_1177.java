package Beecrowd;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayFill2_1177 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] numbers =  new int[1000];

        int limit = scan.nextInt();

        for (int i = 0, count = 0; i < numbers.length; i++) {
            numbers[i] = count++ % limit;
        }
        System.out.println(Arrays.toString(numbers));
    }
}
