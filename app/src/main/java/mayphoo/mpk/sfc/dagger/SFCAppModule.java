package mayphoo.mpk.sfc.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mayphoo.mpk.sfc.SFCNewsApp;
import mayphoo.mpk.sfc.data.models.NewsModel;
import mayphoo.mpk.sfc.mvp.presenters.AddNewsPresenter;
import mayphoo.mpk.sfc.mvp.presenters.NewsListPresenter;

/**
 * Created by User on 1/6/2018.
 */

@Module
public class SFCAppModule {

    private SFCNewsApp mApp;

    public SFCAppModule(SFCNewsApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public NewsModel provideNewsModel(Context context) {
        return new NewsModel(context);
    }

    @Provides
    public NewsListPresenter provideNewsListPresenter() {
        return new NewsListPresenter();
    }

    @Provides
    public AddNewsPresenter provideAddNewsPresenter() {
        return  new AddNewsPresenter();
    }
}
