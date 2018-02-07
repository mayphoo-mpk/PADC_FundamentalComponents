package mayphoo.mpk.sfc.events;

import android.content.Context;

import java.util.List;

import mayphoo.mpk.sfc.data.vo.NewsVO;

/**
 * Created by User on 12/3/2017.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class NewsDataLoadedEvent {
        private int loadedPageIndex;
        private List<NewsVO> loadedNews;
        private Context context;

        public NewsDataLoadedEvent(int loadedPageIndex, List<NewsVO> loadedNews, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedNews = loadedNews;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<NewsVO> getLoadedNews() {
            return loadedNews;
        }

        public Context getContext() {
            return context;
        }
    }
}
