package Beecrowd;

import java.util.Scanner;

public class ArrayFillIV_1179 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] par = new int[5];
        int[] impar = new int[5];
        int parIndex = 0;
        int imparIndex = 0;

        for (int i = 0; i < 15; i++) {

            int number = scan.nextInt();

            if (number % 2 == 0) {
                par[parIndex] = number;
                parIndex++;
                if (parIndex == 5) {
                    for (int j = 0; j < parIndex; j++) {
                        System.out.printf("par[%d] = %d\n", j, par[j]);
                        par[j] = 0;
                    }
                    parIndex = 0;
                }
            } else {
                impar[imparIndex] = number;
                imparIndex++;
                if (imparIndex == 5) {
                    for (int j = 0; j < imparIndex; j++) {
                        System.out.printf("impar[%d] = %d\n", j, impar[j]);
                        impar[j] = 0;
                    }
                    imparIndex = 0;
                }
            }
        }

        for (int i = 0; i < impar.length; i++) {
            if (impar[i] != 0) {
                System.out.printf("impar[%d] = %d\n", i, impar[i]);
            }
        }

        for (int i = 0; i < par.length; i++) {
            if (par[i] != 0) {
                System.out.printf("par[%d] = %d\n", i, par[i]);
            }
        }
    }
}
