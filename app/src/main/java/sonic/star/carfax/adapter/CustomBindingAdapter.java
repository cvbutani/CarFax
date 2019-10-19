package sonic.star.carfax.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import sonic.star.carfax.R;

/**
 * Created by Chirag on 2019-10-17 at 21:29
 * Project - CarFax
 */
public class CustomBindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        if (url != null) {
            Glide
                    .with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .override(950)
                    .into(view);
        }
    }

    @BindingAdapter({"bind:carYear", "bind:carMake", "bind:carModel", "bind:carTrim"})
    public static void carName(TextView view, int year, String make, String model, String trim) {

            view.setText(year + " " + make + " " + model + " " + trim);

    }

    @BindingAdapter({"bind:carPrice", "bind:carMileage"})
    public static void carPriceMileage(TextView view, int price, long mileage) {
        view.setText("$" +price + " | " + mileage + " mi");
    }

}
