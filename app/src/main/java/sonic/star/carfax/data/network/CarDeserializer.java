package sonic.star.carfax.data.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import sonic.star.carfax.data.model.CarListing;

/**
 * Created by Chirag on 2019-10-16 at 20:07
 * Project - CarFax
 */
public class CarDeserializer implements JsonDeserializer<CarListing> {
    @Override
    public CarListing deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context) throws JsonParseException {

        return parseCarListing(json.getAsJsonObject());
    }

    /**
     * Use this method to parse Json response.
     *
     * @param listObject - JsonObject
     * @return parsed Car Detail.
     */
    private CarListing parseCarListing(JsonObject listObject) {
        CarListing carListing = new CarListing();

        carListing.id = getStringOrEmpty(listObject, "id");
        carListing.make = getStringOrEmpty(listObject, "make");
        carListing.model = getStringOrEmpty(listObject, "model");
        carListing.trim = getStringOrEmpty(listObject, "trim");
        carListing.location = getStringOrEmpty(listObject, "location");
        carListing.interiorColor = getStringOrEmpty(listObject, "interiorColor");
        carListing.exteriorColor = getStringOrEmpty(listObject, "exteriorColor");
        carListing.driveType = getStringOrEmpty(listObject, "drivetype");
        carListing.transmission = getStringOrEmpty(listObject, "transmission");
        carListing.engine = getStringOrEmpty(listObject, "engine");
        carListing.bodyStyle = getStringOrEmpty(listObject, "bodytype");
        carListing.fuel = getStringOrEmpty(listObject, "fuel");

        carListing.year = getIntOrZero(listObject, "year");
        carListing.price = getIntOrZero(listObject, "currentPrice");
        carListing.mileage = getIntOrZero(listObject, "mileage");

        JsonObject delaerObject = listObject.getAsJsonObject().getAsJsonObject("dealer");

        carListing.carDealerNumber = getLongOrZero(delaerObject, "phone");

        String city = getStringOrEmpty(delaerObject, "city");
        String state = getStringOrEmpty(delaerObject, "state");
        StringBuilder location = new StringBuilder();

        carListing.location = location.append(city).append(", ").append(state).toString();

        JsonObject imageObject = listObject.getAsJsonObject().getAsJsonObject("images");

        if (imageObject != null) {
            JsonObject firstPhotoObject = imageObject.getAsJsonObject("firstPhoto");
            if (firstPhotoObject != null) {
                carListing.image = getStringOrEmpty(firstPhotoObject, "large");
            } else {
                carListing.image = "";
            }
        } else {
            carListing.image = "";
        }

        return carListing;
    }

    /**
     * Use this method to verify if JsonObject is available or not and key is present in object
     *
     * @param object - JsonObject response
     * @param key - key
     * @return - String value based on key
     */
    private String getStringOrEmpty(JsonObject object, String key) {
        JsonElement element;
        if (object != null) {
            element = object.getAsJsonObject().get(key);
            return element != null ? element.getAsString() : "";
        }
        return "";
    }

    /**
     * Use this method to verify if JsonObject is available or not and key is present in object
     *
     * @param object - JsonObject response
     * @param key - key
     * @return - integer value based on key
     */
    private int getIntOrZero(JsonObject object, String key) {
        JsonElement element;
        if (object != null) {
            element = object.getAsJsonObject().get(key);
            return element != null ? element.getAsInt() : 0;
        }
        return 0;
    }

    /**
     * Use this method to verify if JsonObject is available or not and key is present in object
     *
     * @param object - JsonObject response
     * @param key - key
     * @return - long value based on key
     */
    private long getLongOrZero(JsonObject object, String key) {
        JsonElement element;
        if (object != null) {
            element = object.getAsJsonObject().get(key);
            return element != null ? element.getAsLong() : 0;
        }
        return 0;
    }
}
