package mayphoo.mpk.sfc.mvp.views;

import android.content.Context;

import java.util.List;

import mayphoo.mpk.sfc.data.vo.NewsVO;

/**
 * Created by User on 1/6/2018.
 */

public interface NewsListView {

    void displayNewsList(List<NewsVO> newsList);

    void showLoading();

    void navigateToNewsDetails(NewsVO news);

    Context getContext();

    void showAddNewsScreen();

    void signInGoogle();
}
