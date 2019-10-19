package sonic.star.carfax.adapter;

import android.widget.ImageView;

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
}
