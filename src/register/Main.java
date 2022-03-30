package register;

import java.io.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static String[][] customers = new String[15][5];
    public static int count = 0;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        uploadData();
        while (true) {
            try {

                String cmd = readUserCommand();
                switch (cmd) {
                    case "REGISTER":
                        register();
                        break;
                    case "PRINT":
                        print();
                        break;
                    case "REMOVE":
                        System.out.print("ID: ");
                        int index = scan.nextInt() - 1;
                        scan.nextLine();
                        remove(index);
                        break;
                    case "COUNT":
                        System.out.println(count);
                        break;
                    case "EXIT":
                        savaData();
                        return;
                }
            } catch (RuntimeException exception) {
                exception.printStackTrace();
                System.out.println();
            }
        }
    }

    public static String readUserCommand() {
        System.out.print("Command: ");
        String command = scan.nextLine().trim().toUpperCase(Locale.ROOT);
        switch (command) {
            case "REGISTER":
            case "PRINT":
            case "REMOVE":
            case "COUNT":
            case "EXIT":
                return command;

            default:
                throw new RuntimeException("Invalid command " + command);
        }
    }

    public static void remove(int index) {
        count--;
        customers[index] = customers[count];
        customers[count] = null;
    }

    public static void register() {
        Customer customer = new Customer();
        System.out.print("Firstname: ");
        customer.firstName = scan.nextLine();

        System.out.print("Lastname: ");
        customer.lastName = scan.nextLine();

        System.out.print("Date of birth: ");
        customer.dateOfBirth = LocalDate.parse(scan.nextLine());

        System.out.print("Email: ");
        customer.email = scan.nextLine();

        System.out.print("Phone number: ");
        customer.phoneNumber = scan.nextLine();

        count++;
    }

    public static void uploadData() {
        String pathToFIle = "C:\\Users\\Rnurd\\IdeaProjects\\javamega\\data\\data.txt";
        File file = new File(pathToFIle);
        try {

            Scanner scanningFile = new Scanner(file);

            while (scanningFile.hasNextLine()) {
                Customer customer = new Customer();
                customer.firstName = scanningFile.next();
                customer.lastName = scanningFile.next();
                customer.dateOfBirth = LocalDate.parse(scanningFile.next());
                customer.email = scanningFile.next();
                customer.phoneNumber = scanningFile.nextLine();

//                customers[count] = customer;
                count++;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public static void savaData() {
        String pathToFIle = "C:\\Users\\Rnurd\\IdeaProjects\\javamega\\data\\data.txt";
        File file = new File(pathToFIle);

        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(new FileOutputStream(file, true));

            for (int i = 0; i < count; i++) {
                for (int j = 0; j < customers[i].length; j++) {
                    out.print(customers[i][j] + " | ");
                }
                out.println();
            }

            out.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void print() {
        for (int i = 0; i < count; i++) {
            System.out.println((customers[i].toString()));
        }
    }
}
