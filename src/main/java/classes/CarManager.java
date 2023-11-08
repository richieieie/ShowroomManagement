package main.java.classes;

import main.java.classes.com.*;
import main.java.utils.Inputter;

import java.util.Comparator;
import java.util.Scanner;

public class CarManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BrandList brandList = new BrandList();
        CarList carsList = new CarList(brandList);

        // Load data from brands file
        // Use these lines when you want to load test data
        brandList.loadFromFile("src/main/resources/data/brandsraw.csv");
        carsList.loadFromFile("src/main/resources/data/carsraw.csv");
        // Use these lines with real user's input data
//        brandList.loadFromFile("src/main/resources/data/brands.csv");
//        carsList.loadFromFile("src/main/resources/data/cars.csv");

        int choice;
        do {
            choice = Menu.print("1- List all brands\n" +
                    "2- Add a new brand\n" +
                    "3- Search a brand based on its ID\n" +
                    "4- Update a brand\n" +
                    "5- Save brands to the file, named brands.csv\n" +
                    "6- List all cars in ascending order of brand names\n" +
                    "7- List cars based on a part of an input brand name\n" +
                    "8- Add a car\n" +
                    "9- Remove a car based on its ID\n" +
                    "10- Update a car based on its ID\n" +
                    "11- Save cars to file, named cars.csv\n" + "12- Quit\n" + "Your option:", 1,
                    12);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    String id = Inputter.inputString("Enter brand's ID need to be searched:");
                    int pos = brandList.searchID(id);
                    if (pos < 0) {
                        System.out.println("Brand is not found");
                    } else {
                        System.out.println(brandList.get(pos));
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("src/main/resources/data/brands.csv");
                    break;
                case 6:
                    CarList newCarList = (CarList) carsList.clone();
                    newCarList.sort(Comparator.comparing(c -> c.getBrand().getBrandName()));
                    newCarList.listCars();
                    break;
                case 7:
                    carsList.printBasedBrandName();
                    break;
                case 8:
                    carsList.addCar();
                    break;
                case 9:
                    carsList.removeCar();
                    break;
                case 10:
                    carsList.updateCar();
                    break;
                case 11:
                    carsList.saveToFile("src/main/resources/data/cars.csv");
                    break;
            }
        } while (choice != 12);
    }
}

