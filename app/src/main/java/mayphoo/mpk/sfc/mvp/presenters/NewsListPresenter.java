package mayphoo.mpk.sfc.mvp.presenters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mayphoo.mpk.sfc.SFCNewsApp;
import mayphoo.mpk.sfc.activities.NewsDetailsActivity;
import mayphoo.mpk.sfc.data.models.NewsModel;
import mayphoo.mpk.sfc.data.vo.NewsVO;
import mayphoo.mpk.sfc.delegates.NewsItemDelegate;
import mayphoo.mpk.sfc.mvp.views.NewsListView;

/**
 * Created by User on 1/6/2018.
 */

public class NewsListPresenter extends BasePresenter<NewsListView> implements NewsItemDelegate{

    @Inject
    NewsModel mNewsModel;

    public NewsListPresenter(){
    }

    @Override
    public void onCreate(NewsListView view) {
        super.onCreate(view);
        SFCNewsApp sfcNewsApp = (SFCNewsApp) mView.getContext();
        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        /*List<NewsVO> newsList = mNewsModel.getNews();
        if(!newsList.isEmpty()){
            mView.displayNewsList(newsList);
        } else {
            mView.showLoading();
        }*/
    }

    @Override
    public void onStop() {

    }

    public void onNewsListEndReach(Context context){
        mNewsModel.loadMoreNews(context);
    }

    public void onForceRefresh(Context context){
        mNewsModel.forceRefreshNews(context);
    }

    public void onDataLoaded(Context context, Cursor cursor){
        if(cursor != null && cursor.moveToFirst()){
            List<NewsVO> newsList = new ArrayList<>();

            do {
                NewsVO news = NewsVO.parseFromCursor(context, cursor);
                newsList.add(news);
            } while (cursor.moveToNext());
            mView.displayNewsList(newsList);
        }
    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatistics() {

    }

    @Override
    public void onTapNews(NewsVO news) {
        mView.navigateToNewsDetails(news);
    }

    public void onSucessGoogleSignIn(GoogleSignInAccount googleSignInAccount){
        mNewsModel.authenticateUserWithGoogleAccount(googleSignInAccount, new NewsModel.UserAuthenticateDelegate() {
            @Override
            public void onSuccessAuthenticate(GoogleSignInAccount account) {
                Log.d(SFCNewsApp.LOG_TAG, "onSuccessAuthenticate: " + account.getDisplayName());
            }

            @Override
            public void onFailureAuthenticate(String errorMsg) {
                Log.d(SFCNewsApp.LOG_TAG, "onFailureAuthenticate: " + errorMsg);
            }
        });
    }

    public void onStartPublishingNews() {
        if(mNewsModel.isUserAuthenticate()) {
            mView.showAddNewsScreen();
        } else {
            mView.signInGoogle();
        }
    }
}
