package sonic.star.carfax.data.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import sonic.star.carfax.data.model.CarListing;

/**
 * Created by Chirag on 2019-10-16 at 20:06
 * Project - CarFax
 */
public class RetrofitClient {
    private static IApiService mApiService;
    private static final String BASE_URL = "https://carfax-for-consumers.firebaseio.com/";

    public static IApiService getApiService() {

        if (mApiService == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(CarListing.class, new CarDeserializer())
                    .create();

            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            mApiService = retrofit.create(IApiService.class);
        }
        return mApiService;
    }
}
