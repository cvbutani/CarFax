package sonic.star.carfax.data.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import sonic.star.carfax.data.model.CarFax;

/**
 * Created by Chirag on 2019-10-16 at 20:04
 * Project - CarFax
 */
public interface IApiService {

    @GET("assignment.json")
    Flowable<CarFax> getCarListings();
}
