package mayphoo.mpk.sfc.events;

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

        public NewsDataLoadedEvent(int loadedPageIndex, List<NewsVO> loadedNews) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedNews = loadedNews;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<NewsVO> getLoadedNews() {
            return loadedNews;
        }
    }
}
