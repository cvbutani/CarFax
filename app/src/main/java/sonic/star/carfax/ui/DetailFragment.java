package sonic.star.carfax.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import sonic.star.carfax.R;
import sonic.star.carfax.databinding.DetailFragmentBinding;

public class DetailFragment extends Fragment {

    private DetailViewModel mViewModel;
    private DetailFragmentBinding mBinding;
    private String id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  system back button callback to previous fragment
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getView() != null) {
                    Navigation.findNavController(getView()).navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment());
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
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
            mBinding.info.setVisibility(View.VISIBLE);
            mBinding.progressLoacing.setVisibility(View.GONE);
            mBinding.setCarInfo(car);
            mBinding.carDealerNumber.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + car.carDealerNumber));

                startActivity(intent);
            });
        });

        mViewModel.error.observe(this, e -> {
            Log.e(getClass().getSimpleName(), e);
            mBinding.info.setVisibility(View.GONE);
            mBinding.progressLoacing.setVisibility(View.VISIBLE);
        });
    }
}
