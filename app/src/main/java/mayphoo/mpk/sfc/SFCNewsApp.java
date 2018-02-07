package mayphoo.mpk.sfc;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import mayphoo.mpk.sfc.dagger.DaggerSFCAppComponent;
import mayphoo.mpk.sfc.dagger.NetworkModule;
import mayphoo.mpk.sfc.dagger.SFCAppComponent;
import mayphoo.mpk.sfc.dagger.SFCAppModule;
import mayphoo.mpk.sfc.dagger.UtilsModule;
import mayphoo.mpk.sfc.data.models.NewsModel;

/**
 * Created by User on 11/4/2017.
 * This class represents application component.
 */

public class SFCNewsApp extends Application {

    public static final String LOG_TAG = "SFCNewsApp";

    private SFCAppComponent mSFCAppComponent;

    @Inject
    Context mContext;

    @Inject
    NewsModel mNewsModel;

    @Override
    public void onCreate() {
        super.onCreate();

        mSFCAppComponent = initDagger(); //dagger init
        mSFCAppComponent.inject(this); //register consumer

        mNewsModel.startLoadingMMNews(getApplicationContext());

        Log.d(LOG_TAG, "mContext : " + mContext);
    }

    //initialize Dagger for DI (Dependency Injection)
    private SFCAppComponent initDagger() {
        //return null;
        return DaggerSFCAppComponent.builder()
                .sFCAppModule(new SFCAppModule(this))
                .utilsModule(new UtilsModule())
                .networkModule(new NetworkModule())
                .build();

    }

    public SFCAppComponent getSFCAppComponent() {
        return mSFCAppComponent;
    }
}
