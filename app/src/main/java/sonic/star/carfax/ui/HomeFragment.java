package sonic.star.carfax.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import sonic.star.carfax.R;
import sonic.star.carfax.adapter.HomeAdapter;
import sonic.star.carfax.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment implements HomeAdapter.OnItemClickListener {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding mBinding;
    private HomeAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mAdapter = new HomeAdapter(this);
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

    private void homePageCarDetails() {
        mViewModel.carList().observe(this, carListings -> {
            mBinding.recyclerView.setVisibility(View.VISIBLE);
            mBinding.progressLoacing.setVisibility(View.GONE);

            mAdapter.setCarInfo(carListings);
            mBinding.recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        });

        mViewModel.errorMessage.observe(this, error -> {
            mBinding.recyclerView.setVisibility(View.GONE);
            mBinding.progressLoacing.setVisibility(View.VISIBLE);

            Log.e(getClass().getSimpleName(), error);
        });
    }

    @Override
    public void onItemCLicked(Long number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"  + number));

        startActivity(intent);
    }
}
