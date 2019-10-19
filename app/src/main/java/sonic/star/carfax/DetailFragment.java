package sonic.star.carfax;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sonic.star.carfax.databinding.DetailFragmentBinding;

public class DetailFragment extends Fragment {

    private DetailViewModel mViewModel;
    public DetailFragmentBinding mBinding;
    private String id;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            id = DetailFragmentArgs.fromBundle(getArguments()).getId();
        }
        mBinding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false);
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mBinding.toolbar.setNavigationOnClickListener(view -> Navigation.findNavController(view).navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment()));
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        displayCarInfo();
    }

    private void displayCarInfo() {
        mViewModel.getCar(id).observe(this, car -> {
            mBinding.setCarInfo(car);
        });
    }
}
