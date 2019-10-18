package sonic.star.carfax;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sonic.star.carfax.data.Repository;
import sonic.star.carfax.data.model.CarListing;

public class HomeViewModel extends AndroidViewModel {

    private boolean network;
    private Context context;
    private CompositeDisposable disposable = new CompositeDisposable();
    MutableLiveData<List<CarListing>> listing = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    private void isConnected() {
        disposable.add(ReactiveNetwork
                .observeNetworkConnectivity(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                    network = connectivity.available();
                }));
    }

    void carList() {
        isConnected();
        Repository.getInstance()
                .getCarLists(network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<CarListing>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(List<CarListing> carListings) {
                        listing.postValue(carListings);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMessage.postValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
