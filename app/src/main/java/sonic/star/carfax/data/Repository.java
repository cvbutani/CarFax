package sonic.star.carfax.data;

import android.content.Context;

import java.util.List;

import io.reactivex.Flowable;
import sonic.star.carfax.data.local.CarFaxDatabase;
import sonic.star.carfax.data.model.CarListing;
import sonic.star.carfax.data.network.IApiService;
import sonic.star.carfax.data.network.RetrofitClient;

/**
 * Created by Chirag on 2019-10-16 at 21:45
 * Project - CarFax
 */
public class Repository {

    private DataManager mDataManager;
    private static Repository sRepository;

    /**
     * Private constructor, so no one can make direct instance of Repository
     *
     * @param context context
     */
    private Repository(Context context) {
       CarFaxDatabase database = CarFaxDatabase.getInstance(context);
       IApiService service = RetrofitClient.getApiService();
       mDataManager = new DataManager(database, service);
    }

    /**
     * Use this method to get the {@link Repository} instance
     *
     * @return {@link Repository} instance
     */
    public static Repository getInstance(){
        if(sRepository == null){
            throw new RuntimeException("Initialize repository with Repository.init(Context)");
        }
        return sRepository;
    }

    /**
     * This will create a single-ton
     *
     * @param context Context
     */
    public static void init(Context context){
        if(sRepository == null) {
            sRepository = new Repository(context);
        }
    }

    /**
     * Use this method to get car list from DataManger
     *
     * @param isConnected - Network status
     * @return if DataManager has car listing then It will return list
     */
    public Flowable<List<CarListing>> getCarLists(boolean isConnected) {
        return mDataManager.getCarListings(isConnected);
    }

    /**
     * Use this method to get one car info based on primary key id
     *
     * @param id - primary key
     * @return car detail
     */
    public Flowable<CarListing> getCarInfoRepo(String id) {
        return mDataManager.getCarInfoFromDb(id);
    }

}
