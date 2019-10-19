package sonic.star.carfax.data.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.StringJoiner;

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

    private CarListing parseCarListing(JsonObject listObject) {
        CarListing carListing = new CarListing();


        JsonElement idElement = listObject.getAsJsonObject().get("id");
        carListing.id = getStringOrEmpty(idElement);

        JsonElement makeElement = listObject.getAsJsonObject().get("make");
        carListing.make = getStringOrEmpty(makeElement);

        JsonElement modelElement = listObject.getAsJsonObject().get("model");
        carListing.model = getStringOrEmpty(modelElement);

        JsonElement trimElement = listObject.getAsJsonObject().get("trim");
        carListing.trim = getStringOrEmpty(trimElement);

        JsonElement locationElement = listObject.getAsJsonObject().get("location");
        carListing.location = getStringOrEmpty(locationElement);

        JsonElement yearElement = listObject.getAsJsonObject().get("year");
        carListing.year = getIntOrZero(yearElement);

        JsonElement priceElement = listObject.getAsJsonObject().get("currentPrice");
        carListing.price = getIntOrZero(priceElement);

        JsonElement mileageElement = listObject.getAsJsonObject().get("mileage");
        carListing.mileage = getIntOrZero(mileageElement);

        JsonElement interiorColorElement = listObject.getAsJsonObject().get("interiorColor");
        carListing.interiorColor = getStringOrEmpty(interiorColorElement);

        JsonElement exteriorColorElement = listObject.getAsJsonObject().get("exteriorColor");
        carListing.exteriorColor = getStringOrEmpty(exteriorColorElement);

        JsonElement driveTypeElement = listObject.getAsJsonObject().get("drivetype");
        carListing.driveType = getStringOrEmpty(driveTypeElement);

        JsonElement transmissionElement = listObject.getAsJsonObject().get("transmission");
        carListing.transmission = getStringOrEmpty(transmissionElement);

        JsonElement engineElement = listObject.getAsJsonObject().get("engine");
        carListing.engine = getStringOrEmpty(engineElement);

        JsonElement bodyStyleElement = listObject.getAsJsonObject().get("bodytype");
        carListing.bodyStyle = getStringOrEmpty(bodyStyleElement);

        JsonElement fuelElement = listObject.getAsJsonObject().get("fuel");
        carListing.fuel = getStringOrEmpty(fuelElement);

        JsonObject delaerObject = listObject.getAsJsonObject().getAsJsonObject("dealer");

        JsonElement dealerElement = delaerObject.get("phone");
        carListing.carDealerNumber = getLongOrZero(dealerElement);

        JsonElement cityElements = delaerObject.get("city");
        JsonElement stateElements = delaerObject.get("state");

        String city = getStringOrEmpty(cityElements);
        String state = getStringOrEmpty(stateElements);

        StringBuilder location = new StringBuilder();
        carListing.location = location.append(city).append(", ").append(state).toString();


        JsonObject imageObject = listObject.getAsJsonObject().getAsJsonObject("images");
        JsonElement imageElement;
        if (imageObject != null) {
            JsonObject firstPhotoObject = imageObject.getAsJsonObject("firstPhoto");
            if (firstPhotoObject != null) {
                imageElement = firstPhotoObject.get("large");
            } else {
                imageElement = null;
            }
        } else {
            imageElement = null;
        }
        carListing.image = getStringOrEmpty(imageElement);
        return carListing;
    }

    private String getStringOrEmpty(JsonElement element) {
        return element != null ? element.getAsString() : "";
    }

    private int getIntOrZero(JsonElement element) {
        return element != null ? element.getAsInt() : 0;
    }

    private long getLongOrZero(JsonElement element) {
        return element != null ? element.getAsLong() : 0;
    }
}
