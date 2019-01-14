package dk.dtu.isaacirani.kirurgisksimulator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkChangeReceiver  extends BroadcastReceiver {

    public static final String NETWORK_CHANGE_ACTION = "dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver";

    @Override
    public void onReceive(final Context c, final Intent i) {

        if (isConnected(c)) {
            broadcastSending(c, true);

        } else {
            broadcastSending(c, false);
        }
    }


    private void broadcastSending(Context c, boolean isConnected) {
        try {
            Intent intent = new Intent();
            intent.putExtra("networkstatus", isConnected);
            intent.setAction(NETWORK_CHANGE_ACTION);
            c.sendBroadcast(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean isConnected(Context c) {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()){
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}