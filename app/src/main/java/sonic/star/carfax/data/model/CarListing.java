package sonic.star.carfax.data.model;

import androidx.room.Entity;

/**
 * Created by Chirag on 2019-10-16 at 19:36
 * Project - CarFax
 */
@Entity
public class CarListing implements ICarListing {

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

    public CarListing() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCarimage() {
        return image;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getTrim() {
        return trim;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public long getMileage() {
        return mileage;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public long getCarDealerNumber() {
        return carDealerNumber;
    }

    @Override
    public String getInteriorColor() {
        return interiorColor;
    }

    @Override
    public String getExteriorColor() {
        return exteriorColor;
    }

    @Override
    public String getDriveType() {
        return driveType;
    }

    @Override
    public String getTransmission() {
        return transmission;
    }

    @Override
    public String getEngine() {
        return engine;
    }

    @Override
    public String getBodyStyle() {
        return bodyStyle;
    }
}
