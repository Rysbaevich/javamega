package Beecrowd;

import java.util.Scanner;

public class LowestNumberAndPosition_1180 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] numbers = new int[scan.nextInt()];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = scan.nextInt();
        }

        int lowestPosition = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < numbers[lowestPosition]) {
                lowestPosition = i;
            }
        }

        System.out.println("Menor valor: " + numbers[lowestPosition]);
        System.out.println("Posicao: " + lowestPosition);
    }
}

