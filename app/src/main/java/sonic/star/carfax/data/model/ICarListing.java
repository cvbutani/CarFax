package sonic.star.carfax.data.model;

/**
 * Created by Chirag on 2019-10-16 at 19:58
 * Project - CarFax
 */
public interface ICarListing {
    String getId();

    String getCarimage();

    int getYear();

    String getMake();

    String getModel();

    String getTrim();

    int getPrice();

    long getMileage();

    String getLocation();

    long getCarDealerNumber();

    String getInteriorColor();

    String getExteriorColor();

    String getDriveType();

    String getTransmission();

    String getEngine();

    String getBodyStyle();
}
