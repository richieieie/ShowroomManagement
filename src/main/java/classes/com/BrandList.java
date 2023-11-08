package main.java.classes.com;

import main.java.utils.CustomErrors;
import main.java.utils.Inputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList extends ArrayList<Brand> {
    public BrandList() {

    }

    /**
     * Read data from a csv file
     *
     * @param fileName This is the file's path name
     * @return boolean This returns true if reading to file is success and vice versa
     */
    public boolean loadFromFile(String fileName) {
        try {
            File f = new File(fileName);
            Scanner scanner = new Scanner(f);

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(", ");
                add(new Brand(data[0], data[1], data[2], Double.parseDouble(data[3])));
            }

            scanner.close();
            return false;
        } catch (
                FileNotFoundException e) {
            return false;
        }
    }

    /**
     * Open the file based on the filename to write data in line-by-line in text format
     *
     * @param fileName This is the file's path name
     * @return boolean This returns true if writing to file is successful and vice versa
     */
    public boolean saveToFile(String fileName) {
        try {
            new FileWriter(fileName).close();
            FileWriter fw = new FileWriter(fileName, true);

            fw.write("ID, Name, Sound, Price");
            for (Brand b : this) {
                fw.write("\n" + b.toString());
            }

            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Find the position of a Brand Object in a BrandList by using brandID
     *
     * @param ID This is brandID
     * @return int This returns index of the Brand Object
     */
    public int searchID(String ID) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getBrandID().equals(ID.toUpperCase()))
                return i;
        }
        return -1;
    }

    /**
     * List all the available Brands for helping user to choose Brand when add or update a Car
     * Object in
     * a CarList
     *
     * @return Brand This returns a Brand in a BrandList
     */
    public Brand getUserChoice() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            Brand b = get(i);
            String data = String.format("%d/- %s, %s, %s, %.3f\n", i + 1, b.getBrandID(),
                    b.getBrandName(), b.getSoundBrand(), b.getPrice());
            sb.append(data);
        }
        sb.append("Choose your brand's ID:");
        try {
            int choice = Menu.print(sb.toString(), 1, size());
            return get(choice - 1);
        } catch (CustomErrors.InvalidInputArg e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add a new Brand Object to a BrandList
     */
    public void addBrand() {
        String id = Inputter.inputString("Brand's ID:").toUpperCase();
        String name = Inputter.inputString("Brand's name:");
        String sound = Inputter.inputString("Brand's sound:");
        double price = Inputter.inputDouble("Brand's price:");
        add(new Brand(id, name, sound, price));
    }

    /**
     * Update a Brand in a BrandList
     */
    public void updateBrand() {
        String id = Inputter
                .inputString("Enter brand's ID need to be updated:")
                .toUpperCase();

        int pos = searchID(id);
        if (pos < 0) {
            System.out.println("Brand is not found");
        } else {
            String name = Inputter.inputString("Enter new brand's name:");
            String sound = Inputter.inputString("Enter new brand's sound:");
            double price = Inputter.inputDouble("Enter new brand's price:");
            set(pos, new Brand(id, name, sound, price));
        }
    }

    /**
     * List all brands in a BrandList
     */
    public void listBrands() {
        for (Brand b : this) {
            System.out.println(b);
        }
    }

}
