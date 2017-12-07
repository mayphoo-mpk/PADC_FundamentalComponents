package mayphoo.mpk.sfc.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by User on 12/2/2017.
 */

public abstract class BaseFragment extends Fragment {

    public void checkNetworkConnectivity() {
        Log.d("", "Base Network Connectivity");
    }
}
