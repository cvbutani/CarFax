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

    private Repository(Context context) {
       CarFaxDatabase database = CarFaxDatabase.getInstance(context);
       IApiService service = RetrofitClient.getApiService();
       mDataManager = new DataManager(database, service);
    }

    public static Repository getInstance(){
        if(sRepository == null){
            throw new RuntimeException("Initialize repository with Repository.init(Context)");
        }
        return sRepository;
    }

    public static void init(Context context){
        if(sRepository == null) {
            sRepository = new Repository(context);
        }
    }

    public Flowable<List<CarListing>> getCarLists() {
        return mDataManager.getCarListings();
    }

}
