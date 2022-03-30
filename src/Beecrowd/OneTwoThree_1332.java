package Beecrowd;

import java.util.Scanner;

public class OneTwoThree_1332 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int i = scan.nextInt();
        scan.nextLine();

        for (; i > 0 ; i--) {
            String number = scan.nextLine();

            if (number.length() == 5) {
                System.out.println(3);
            } else if (number.contains("on") || number.contains("ne") || number.startsWith("o") && number.endsWith("e")) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }
    }
}
