package main.java.utils;

import java.util.Scanner;

public class Inputter {
    public static String inputString(String prompt) {
        String s;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(prompt);
            s = scanner.nextLine();
        } while (s.length() == 0);

        return s;
    }

    public static String inputString(String prompt, String regex) {
        String s;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(prompt);
            s = scanner.nextLine().trim();
        } while (s.length() == 0 || !s.matches(regex));

        return s;
    }

    public static int inputInt(String prompt) {
        int num = -1;
        do {
            try {
                String s = inputString(prompt);
                num = Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("invalid data types or number < 0");
            }
        } while (num < 0);
        return num;
    }



    public static double inputDouble(String prompt) {
        double num = -1;
        do {
            try {
                String s = inputString(prompt);
                num = Double.parseDouble(s);
            } catch (Exception e) {
                System.out.println("invalid data types or number < 0");
            }
        } while (num < 0);
        return num;
    }

    public static int inputLimitInt(String prompt, int min, int max) throws NumberFormatException {
        int num;

        String s = inputString(prompt);
        num = Integer.parseInt(s);
        if (num < min || num > max)
            throw new CustomErrors.InvalidInputArg(String.format("Please enter number from %d to %d", min, max));

        return num;
    }
}
