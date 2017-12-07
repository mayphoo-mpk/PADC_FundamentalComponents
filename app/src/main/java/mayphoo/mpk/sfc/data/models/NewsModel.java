package mayphoo.mpk.sfc.data.models;

import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import mayphoo.mpk.sfc.data.vo.NewsVO;
import mayphoo.mpk.sfc.events.RestApiEvents;
import mayphoo.mpk.sfc.network.MMNewsDataAgentImpl;
import mayphoo.mpk.sfc.utils.AppConstants;

/**
 * Created by User on 12/3/2017.
 */

public class NewsModel {

    private static NewsModel objInstance;

    private List<NewsVO> mNews;
    private int mmNewsPageIndex = 1;

    private NewsModel() {
        EventBus.getDefault().register(this);
        mNews = new ArrayList<>();
    }

    public static NewsModel getInstance(){
        if(objInstance == null){
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    public void startLoadingMMNews() {
        MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event){
        mNews.addAll(event.getLoadedNews());
        mmNewsPageIndex = event.getLoadedPageIndex() + 1;
    }

    public NewsVO getNewsDetailsByNewId(String newsId){
        for(NewsVO news : mNews){
            if(TextUtils.equals(news.getNewsId(), newsId)){
                return news;
            }
        }
        return null;
    }

}
