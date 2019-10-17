package sonic.star.carfax.data.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sonic.star.carfax.data.model.CarListing;

/**
 * Created by Chirag on 2019-10-16 at 20:07
 * Project - CarFax
 */
public class CarDeserializer implements JsonDeserializer<List<CarListing>> {
    @Override
    public List<CarListing> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return parseCarListing(json.getAsJsonObject());
    }

    private List<CarListing> parseCarListing(JsonObject listObject) {
        List<CarListing> carListingList = new ArrayList<>();
        JsonArray carArray = listObject.getAsJsonArray("listings");

        for (int i = 0; i < carArray.size(); i++) {
            CarListing carListing = new CarListing();
            if (carArray.get(i) != null) {

                JsonElement idElement = carArray.get(i).getAsJsonObject().get("id");
                carListing.id = getStringOrEmpty(idElement);

                JsonElement makeElement = carArray.get(i).getAsJsonObject().get("make");
                carListing.make = getStringOrEmpty(makeElement);

                JsonElement modelElement = carArray.get(i).getAsJsonObject().get("model");
                carListing.model = getStringOrEmpty(modelElement);

                JsonElement trimElement = carArray.get(i).getAsJsonObject().get("trim");
                carListing.trim = getStringOrEmpty(trimElement);

                JsonElement locationElement = carArray.get(i).getAsJsonObject().get("location");
                carListing.location = getStringOrEmpty(locationElement);

                JsonElement yearElement = carArray.get(i).getAsJsonObject().get("year");
                carListing.year = getIntOrZero(yearElement);

                JsonElement priceElement = carArray.get(i).getAsJsonObject().get("price");
                carListing.price = getIntOrZero(priceElement);

                JsonElement mileageElement = carArray.get(i).getAsJsonObject().get("mileage");
                carListing.mileage = getIntOrZero(mileageElement);

                JsonElement interiorColorElement = carArray.get(i).getAsJsonObject().get("interiorColor");
                carListing.interiorColor = getStringOrEmpty(interiorColorElement);

                JsonElement exteriorColorElement = carArray.get(i).getAsJsonObject().get("exteriorColor");
                carListing.exteriorColor = getStringOrEmpty(exteriorColorElement);

                JsonElement driveTypeElement = carArray.get(i).getAsJsonObject().get("driveType");
                carListing.driveType = getStringOrEmpty(driveTypeElement);

                JsonElement transmissionElement = carArray.get(i).getAsJsonObject().get("transmission");
                carListing.transmission = getStringOrEmpty(transmissionElement);

                JsonElement engineElement = carArray.get(i).getAsJsonObject().get("engine");
                carListing.engine = getStringOrEmpty(engineElement);

                JsonElement bodyStyleElement = carArray.get(i).getAsJsonObject().get("bodyStyle");
                carListing.bodyStyle = getStringOrEmpty(bodyStyleElement);

                JsonObject delaerObject = carArray.get(i).getAsJsonObject().getAsJsonObject("dealer");

                JsonElement dealerElement = delaerObject.get("phone");
                carListing.carDealerNumber = getLongOrZero(dealerElement);

                JsonElement imageElement = carArray.get(i).getAsJsonObject().getAsJsonObject("images").getAsJsonObject("firstPhoto").get("medium");
                carListing.image = getStringOrEmpty(imageElement);
            }
            carListingList.add(carListing);
        }
        return carListingList;
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
