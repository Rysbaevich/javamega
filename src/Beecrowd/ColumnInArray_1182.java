package Beecrowd;

import java.util.Scanner;

public class ColumnInArray_1182 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double[][] numbers = new double[12][12];
        int colUser = scan.nextInt();
        String operation = scan.next();

        for (int row = 0; row < numbers.length; row++) {
            for (int col = 0; col < numbers[row].length; col++) {
                numbers[row][col] = scan.nextDouble();
            }
        }

        double result = 0.0;

        for (int row = 0; row < numbers.length; row++) {
                result += numbers[row][colUser];
        }

        if (operation.equals("M")) {
            result /= numbers.length;
        }
        System.out.printf("%.1f\n", result);
    }
}
