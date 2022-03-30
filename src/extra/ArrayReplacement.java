package extra;

import java.util.Scanner;

public class ArrayReplacement {
    public static void main(String[] args) {
        int[] numbers = new int[10];
        fill(numbers);
        replacement(numbers);
        print(numbers);
    }

    public static int[] fill(int[] numbers) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = scan.nextInt();
        }
        return numbers;
    }

    public static int[] replacement(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] <= 0) {
                numbers[i] = 1;
            }
        }
        return numbers;
    }

    public static void print(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("X[%d] = %d\n", i, numbers[i]);
        }
    }
}
