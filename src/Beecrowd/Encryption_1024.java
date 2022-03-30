package Beecrowd;

import java.util.Scanner;

public class Encryption_1024 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int i = scan.nextInt();
        scan.nextLine();

        for (; i > 0 ; i--) {
            String text = scan.nextLine();

            char[] elements = text.toCharArray();

            for (int j = 0; j < elements.length; j++) {
                if (Character.isAlphabetic(elements[j])) {
                    elements[j] += 3;
                }
            }

            for (int j = 0; j < elements.length / 2; j++) {
                char temp = elements[j];
                elements[j] = elements[elements.length - 1 - j];
                elements[elements.length - 1 - j] = temp;
            }

            for (int j = elements.length / 2; j < elements.length; j++) {
                elements[j] -= 1;
            }
            System.out.println(String.copyValueOf(elements));
        }
    }
}
