package sonic.star.carfax.data.network;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import sonic.star.carfax.data.model.CarFax;
import sonic.star.carfax.data.model.CarListing;

/**
 * Created by Chirag on 2019-10-16 at 20:04
 * Project - CarFax
 */
public interface IApiService {

    @GET("assignment.json")
    Flowable<CarFax> getCarListings();
}
