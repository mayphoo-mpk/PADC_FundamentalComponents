package mayphoo.mpk.sfc.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mayphoo.mpk.sfc.network.MMNewsDataAgent;
import mayphoo.mpk.sfc.network.UITestDataAgentImpl;

/**
 * Created by aung on 1/7/18.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public MMNewsDataAgent provideMMNewsDataAgent() {
        return new UITestDataAgentImpl();
    }
}
