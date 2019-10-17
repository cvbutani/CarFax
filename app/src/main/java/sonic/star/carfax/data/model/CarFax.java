package sonic.star.carfax.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chirag on 2019-10-16 at 22:35
 * Project - CarFax
 */
public class CarFax{

    @SerializedName("listings")
    public List<CarListing> carListingLIst;

    private CarFax() {
    }
}
