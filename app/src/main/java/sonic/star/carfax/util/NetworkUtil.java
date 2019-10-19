package sonic.star.carfax.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.util.Log;

/**
 * Created by Chirag on 2019-10-19 at 13:32
 * Project - CarFax
 */
public class NetworkUtil {

    private Context context;

    public NetworkUtil(Context context) {
        this.context = context;
    }

    /**
     * class checks if phone has internet connection or not
     * @return true if phone is connected, false if phone doesn't have connection
     */
    public boolean checkCellularNetwork() {
        boolean hasCellularTransport;
        boolean hasWifiTransport;
        boolean isConnectedToWifi = false;
        boolean isConnectedToCellular = false;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            Log.e(getClass().getName(), "Connectivity manager not found!");
            return false;
        }

        Network[] networks = connectivityManager.getAllNetworks();
        if (networks.length == 0) {
            Log.e(getClass().getName(), "No network connectivity at all!");
            return false;
        }

        for (Network n : networks) {
            NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(n);
            boolean connected;
            if (nc == null) {
                Log.e(getClass().getName(), "No network connectivity at all!");
                return false;
            }
            hasCellularTransport = nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
            hasWifiTransport = nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connected = connectivityManager.bindProcessToNetwork(n);
                connectivityManager.bindProcessToNetwork(null); //clear the process
                if (hasCellularTransport) {
                    isConnectedToCellular = connected;
                } else if (hasWifiTransport) {
                    isConnectedToWifi = connected;
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                connected = ConnectivityManager.setProcessDefaultNetwork(n);
                if (hasCellularTransport) {
                    isConnectedToCellular = connected;
                } else if (hasWifiTransport) {
                    isConnectedToWifi = connected;
                }
                ConnectivityManager.setProcessDefaultNetwork(null);  //clear the network
            } else {
                Log.e(getClass().getName(), "OS not supported!");
                return false;
            }
        }

        if (isConnectedToCellular && isConnectedToWifi) {
            return true;
        } else if (isConnectedToCellular) {
            return true;
        } else if (isConnectedToWifi) {
            return true;
        } else {
            return false;
        }
    }
}
