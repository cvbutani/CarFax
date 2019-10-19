package sonic.star.carfax.data;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;
import sonic.star.carfax.data.local.CarFaxDatabase;
import sonic.star.carfax.data.model.CarListing;
import sonic.star.carfax.data.network.IApiService;

/**
 * Created by Chirag on 2019-10-16 at 21:46
 * Project - CarFax
 */
class DataManager {

    private CarFaxDatabase mDatabase;
    private IApiService mApiService;

    DataManager(CarFaxDatabase database, IApiService service) {
        mDatabase = database;
        mApiService = service;
    }

    /**
     * Use this method to do a network call to get data and store in database
     *
     * @param isConnected - Network status
     * @return carListing list
     */
    Flowable<List<CarListing>> getCarListings(boolean isConnected) {
        return new NetworkBoundResource<List<CarListing>, List<CarListing>>() {

            //  delete data before updating with new car list
            @Override
            public void saveCallResult(@NonNull List<CarListing> item) {
                mDatabase.carDao().deleteCarInfo();
                mDatabase.carDao().addCar(item);
            }

            //  load car list from database
            @Override
            protected Flowable<List<CarListing>> loadFromDb() {
                return mDatabase.carDao().getCarListings().toFlowable();
            }

            //  create network call using ApiService class
            @Override
            protected Flowable<List<CarListing>> createCall() {
                return mApiService.getCarListings().map(data -> data.carListingLIst);
            }

            //  Based on network it will return true or false
            @Override
            protected boolean shouldFetch() {
                return isConnected;
            }
        }.asFlowable();
    }

    /**
     * load car info from database
     *
     * @param id - primary key
     * @return car detail
     */
    Flowable<CarListing> getCarInfoFromDb(String id) {
        return mDatabase.carDao().getCarInfo(id).toFlowable();
    }
}
