package mayphoo.mpk.sfc.network;

import android.content.Context;

/**
 * Created by User on 12/3/2017.
 */

public interface MMNewsDataAgent {

    //parameter accessToken -> use when call API
    void loadMMNews(String accessToken, int pageNo, Context context);
}
