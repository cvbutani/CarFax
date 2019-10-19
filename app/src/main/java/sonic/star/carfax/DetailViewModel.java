package sonic.star.carfax;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return carInfo;
    }
}
