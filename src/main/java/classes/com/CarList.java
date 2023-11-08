package main.java.classes.com;

import main.java.utils.Inputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CarList extends ArrayList<Car> {
    private BrandList brandList;

    public CarList(BrandList brandList) {
        this.brandList = brandList;
    }

    ;

    public boolean loadFromFile(String fileName) {
        try {
            File f = new File(fileName);
            Scanner scanner = new Scanner(f);

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(", ");

                // use carBrand to find index of brand of brandList
                Brand carBrand = new Brand();
                carBrand.setBrandID(data[1]);

                Car c = new Car(data[0], brandList.get(brandList.indexOf(carBrand)), data[2], data[3]
                        , data[4]);
                if (!contains(c))
                    add(c);
            }

            scanner.close();
            return false;
        } catch (
                FileNotFoundException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveToFile(String fileName) {
        try {
            new FileWriter(fileName).close();
            FileWriter fw = new FileWriter(fileName, true);

            fw.write("ID, Brand, Color, FrameID, EngineID");
            for (Car c : this) {
                fw.write("\n" + c.toString());
            }

            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public int searchID(String ID) {
        Car c = new Car();
        c.setCarID(ID);
        return indexOf(c);
    }

    public int searchFrame(String fID) {
        Car c = new Car();
        c.setFrameID(fID);
        return indexOf(c);
    }

    public int searchEngine(String eID) {
        Car c = new Car();
        c.setEngineID(eID);
        return indexOf(c);
    }

    public void addCar() {
        String cID = Inputter.inputString("Car's ID:", "[Cc]\\d{2}").toUpperCase();
        String color = Inputter.inputString("Car's color:");
        String fID = Inputter.inputString("Car's frame ID:", "[Ff]\\d{5}").toUpperCase();
        String eID = Inputter.inputString("Car's engine ID:", "[Ee]\\d{5}").toUpperCase();

        // Get object brand in brandList
        Brand b = brandList.getUserChoice();

        Car c = new Car(cID, b, color, fID, eID);
        if (contains(c))
            System.out.println("this car already exists");
        else
            add(c);
    }

    public void printBasedBrandName() {
        String brandName = Inputter.inputString("Enter brand's name:").toUpperCase();
        int count = 0;

        for (Car c : this) {
            if (c.getBrand().getBrandName().toUpperCase().contains(brandName)) {
                System.out.println(c.screenString());
                count++;
            }
        }

        if (count == 0)
            System.out.println("No car is detected!");
    }

    public boolean removeCar() {
        String ID = Inputter.inputString("Enter car's ID:").toUpperCase();

        int i = searchID(ID);
        if (i < 0) {
            System.out.println("No car is detected!");
            return false;
        }
        ;

        remove(i);
        System.out.println("Car with ID " + ID + " was removed!");
        return true;
    }

    public boolean updateCar() {
        String ID = Inputter.inputString("Enter car's ID:").toUpperCase();

        int i = searchID(ID);

        if (i < 0) {
            System.out.println("No car is detected!");
            return false;
        }
        Brand b = brandList.getUserChoice();
        String color = Inputter.inputString("new car's color:");
        String fID, eID;
        do {
            fID = Inputter.inputString("New car's frame ID:", "[Ff]\\d{5}").toUpperCase();
            eID = Inputter.inputString("New car's engine ID:", "[Ee]\\d{5}").toUpperCase();
            if (searchFrame(fID) > 0 || searchEngine(eID) > 0)
                System.out.println("FrameID or EngineID is duplicated");
        } while (searchFrame(fID) > 0 || searchEngine(eID) > 0);

        set(i, new Car(ID, b, color, fID, eID));

        return true;
    }

    public void listCars() {
        forEach(c -> System.out.println(c.screenString()));
    }

}
