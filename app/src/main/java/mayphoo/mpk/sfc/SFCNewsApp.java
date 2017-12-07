package mayphoo.mpk.sfc;

import android.app.Application;

import mayphoo.mpk.sfc.data.models.NewsModel;

/**
 * Created by User on 11/4/2017.
 * This class represents application component.
 */

public class SFCNewsApp extends Application {

    public static final String LOG_TAG = "SFCNewsApp"; //constant variable

    @Override
    public void onCreate() {
        super.onCreate();
        NewsModel.getInstance().startLoadingMMNews();
    }
}
