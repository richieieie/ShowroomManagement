package main.java.classes.com;

import main.java.utils.CustomErrors;
import main.java.utils.Inputter;

public class Menu {
    public static int print(String prompt, int min, int max) {
        int choice;
        do {
            try {
                choice = Inputter.inputLimitInt(prompt, min, max);
            } catch (CustomErrors.InvalidInputArg e) {
                System.out.println(e.getMessage());
                choice = -1;
            } catch (NumberFormatException e) {
                System.out.println("Options must be a positive number");
                choice = -1;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                choice = -1;
            }
        } while (choice < 0);

        return choice;
    }
}
