package Beecrowd;

import java.util.Scanner;

public class LineInArray_1181 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        double[][] numbers = new double[12][12];

        int rowUser = scan.nextInt();
        String operation = scan.next();

        for (int row = 0; row < numbers.length; row++) {
            for (int col = 0; col < numbers[row].length; col++) {
                numbers[row][col] = scan.nextDouble();
            }
        }

        double result = 0.0;

        for (int col = 0; col < numbers[rowUser].length; col++) {
            result += numbers[rowUser][col];
        }

        if (operation.equals("M")) {
            result /= numbers[rowUser].length;
        }

        System.out.printf("%.1f\n", result);

    }
}
