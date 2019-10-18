package sonic.star.carfax;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sonic.star.carfax.adapter.HomeAdapter;
import sonic.star.carfax.data.model.CarListing;
import sonic.star.carfax.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding mBinding;
    private HomeAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mAdapter = new HomeAdapter();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        homePageCarDetails();
    }

    private void homePageCarDetails(){
        mViewModel.listing.observe(this, carListings -> {
            mAdapter.setCarInfo(carListings);
            mBinding.recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        });

        mViewModel.errorMessage.observe(this, error -> {
            Log.i("ERROR", error);
        });
    }
}
