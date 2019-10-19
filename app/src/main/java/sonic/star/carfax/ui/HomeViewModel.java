package sonic.star.carfax.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sonic.star.carfax.data.Repository;
import sonic.star.carfax.data.model.CarListing;
import sonic.star.carfax.util.NetworkUtil;

public class HomeViewModel extends AndroidViewModel {

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<CarListing>> listing = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        carList();
    }

    /**
     * Requests carListing and wrap it in MutableLiveData
     *
     * @return carListing list
     */
    LiveData<List<CarListing>> carList() {
        NetworkUtil util = new NetworkUtil(getApplication());
        Repository.getInstance()
                .getCarLists(util.checkCellularNetwork())
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
        return listing;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();   // Always dispose disposable when app is closed.
    }
}
