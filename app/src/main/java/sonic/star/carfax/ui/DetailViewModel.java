package sonic.star.carfax.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sonic.star.carfax.data.Repository;
import sonic.star.carfax.data.model.CarListing;

public class DetailViewModel extends AndroidViewModel {

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<CarListing> carInfo = new MutableLiveData<>();
    MutableLiveData<String> error = new MutableLiveData<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Requests car detail and wrap it in MutableLiveData
     *
     * @return car detail
     */
    LiveData<CarListing> getCar(String id) {
        Repository.getInstance()
                .getCarInfoRepo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<CarListing>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(CarListing carListing) {
                        carInfo.postValue(carListing);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return carInfo;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();   // Always dispose disposable when app is closed.
    }
}
