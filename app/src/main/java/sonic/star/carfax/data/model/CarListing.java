package sonic.star.carfax.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Chirag on 2019-10-16 at 19:36
 * Project - CarFax
 */
@Entity
public class CarListing implements ICarListing {

    @PrimaryKey
    @NonNull
    public String id;
    public String image;
    public int year;
    public String make;
    public String model;
    public String trim;
    public int price;
    public long mileage;
    public String location;

    public long carDealerNumber;
    public String interiorColor;
    public String exteriorColor;
    public String driveType;
    public String transmission;
    public String engine;
    public String bodyStyle;
    public String fuel;

    public CarListing() {
    }

    @Override
    public String getMake() {
        if (trim.equals("Unspecified")) {
            return year + " " + make + " " + model;
        }
        return year + " " + make + " " + model + " " + trim;
    }

    @Override
    public String getPriceMileage() {
        return "$" +price + " | " + mileage + " mi";
    }
}
