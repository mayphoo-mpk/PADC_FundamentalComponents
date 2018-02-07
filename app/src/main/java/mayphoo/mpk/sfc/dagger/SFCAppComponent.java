package mayphoo.mpk.sfc.dagger;

import javax.inject.Singleton;

import dagger.Component;
import mayphoo.mpk.sfc.SFCNewsApp;
import mayphoo.mpk.sfc.activities.AddNewsActivity;
import mayphoo.mpk.sfc.activities.NewsListActivity;
import mayphoo.mpk.sfc.data.models.NewsModel;
import mayphoo.mpk.sfc.mvp.presenters.AddNewsPresenter;
import mayphoo.mpk.sfc.mvp.presenters.NewsListPresenter;

/**
 * Created by User on 1/6/2018.
 */

@Component(modules = {SFCAppModule.class, UtilsModule.class, NetworkModule.class})
@Singleton
public interface SFCAppComponent {

    void inject(SFCNewsApp app);

    void inject(NewsModel newsModel);    //for DataAgentImpl dependency

    void inject(NewsListPresenter newsListPresenter);

    void inject(NewsListActivity newsListActivity);

    void inject(AddNewsPresenter addNewsPresenter);

    void inject(AddNewsActivity addNewsActivity);
}
