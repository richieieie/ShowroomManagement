package main.java.classes.com;

import java.util.Objects;

public class Car implements Comparable<Car> {
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
        this.carID = "";
        this.brand = new Brand();
        this.color = "";
        this.frameID = "";
        this.engineID = "";
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    /**
     * Compare brandName of two brands
     *
     * @param o the object to be compared.
     * @return int This returns the result of the comparison
     */
    @Override
    public int compareTo(Car o) {
        int d = brand.getBrandName().compareTo(o.brand.getBrandName());

        if (d == 0)
            return carID.compareTo(o.carID);

        return d;
    }

    /**
     * Associating fields to a string for writing a car to file
     *
     * @return String This returns a car's formatted string to be available in csv file
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", carID, brand.getBrandID(), color, frameID,
                engineID);
    }

    /**
     * Associating fields to a string for outputting a car to screen
     *
     * @return String This returns a car's formatted string to be shown in console
     */
    public String screenString() {
        return String.format("%s\n%s, %s, %s, %s", brand, carID, color, frameID,
                engineID);
    }

    /**
     * Override equals method for checking duplicated of a Car in CarList
     *
     * @return boolean This returns true if carIDs or frameIDs or engineIDs of both are equal and
     * vice versa
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carID, car.carID) || Objects.equals(frameID, car.frameID) ||
                Objects.equals(engineID, car.engineID);
    }

}
