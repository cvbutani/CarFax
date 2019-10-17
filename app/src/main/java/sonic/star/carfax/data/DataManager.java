package sonic.star.carfax.data;

import android.provider.ContactsContract;

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
public class DataManager {

    private CarFaxDatabase mDatabase;
    private IApiService mApiService;

    DataManager(CarFaxDatabase database, IApiService service) {
        mDatabase = database;
        mApiService = service;
    }

    Flowable<List<CarListing>> getCarListings() {
        return new NetworkBoundResource<List<CarListing>, List<CarListing>>() {

            @Override
            public void saveCallResult(@NonNull List<CarListing> item) {
                mDatabase.carDao().deleteCarInfo();
                mDatabase.carDao().addCar(item);
            }

            @Override
            protected Flowable<List<CarListing>> loadFromDb() {
                return mDatabase.carDao().getCarListings().toFlowable();
            }

            @Override
            protected Flowable<List<CarListing>> createCall() {
                return mApiService.getCarListings();
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }
        }.asFlowable();
    }
}
