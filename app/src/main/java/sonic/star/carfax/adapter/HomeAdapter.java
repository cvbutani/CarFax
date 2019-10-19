package sonic.star.carfax.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sonic.star.carfax.DetailFragment;
import sonic.star.carfax.HomeFragment;
import sonic.star.carfax.HomeFragmentDirections;
import sonic.star.carfax.R;
import sonic.star.carfax.data.model.CarListing;
import sonic.star.carfax.databinding.HomeFragmentBinding;
import sonic.star.carfax.databinding.HomeRecyclerItemBinding;

/**
 * Created by Chirag on 2019-10-17 at 21:54
 * Project - CarFax
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<CarListing> carInfo;
    private OnItemClickListener clickListener;


    public interface OnItemClickListener {
        void onItemCLicked(Long number);
    }

     public HomeAdapter(OnItemClickListener listener){
            clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeRecyclerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.home_recycler_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.mItemBinding.setCarInfo(carInfo.get(position));
        holder.mItemBinding.carCard.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(carInfo.get(position).id));
        });
        holder.mItemBinding.carDealerNumber.setOnClickListener(v -> {
            clickListener.onItemCLicked(carInfo.get(position).carDealerNumber);
        });
    }

    @Override
    public int getItemCount() {
        return carInfo.size();
    }

    public void setCarInfo(List<CarListing> list) {
        carInfo = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        HomeRecyclerItemBinding mItemBinding;

        ViewHolder(@NonNull HomeRecyclerItemBinding itemView) {
            super(itemView.getRoot());
            mItemBinding = itemView;
        }
    }
}
