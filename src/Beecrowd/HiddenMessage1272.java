package Beecrowd;

import java.util.Scanner;

public class HiddenMessage1272 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        scan.nextLine();
        String text1 = "";

        for (; i > 0; i--) {
            String text = scan.nextLine();
            char[] array = text.toCharArray();
            int c = 0;
            if (array[0] != ' ') {
                text1 += array[0];
                c++;
            }

            for (int j = 1; j < array.length; j++) {
                if (array[j - 1] == ' ') {
                    text1 += array[j];
                    c++;
                }
            }
        }
        System.out.println(text1);
    }
}
