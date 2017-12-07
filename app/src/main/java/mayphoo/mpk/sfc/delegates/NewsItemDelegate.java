package mayphoo.mpk.sfc.delegates;

import mayphoo.mpk.sfc.data.vo.NewsVO;

/**
 * Created by User on 11/11/2017.
 */

public interface NewsItemDelegate {

    void onTapComment();
    void onTapSendTo();
    void onTapFavorite();
    void onTapStatistics();
    void onTapNews(NewsVO news);
}
