package mayphoo.mpk.sfc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mayphoo.mpk.sfc.R;
import mayphoo.mpk.sfc.data.vo.NewsVO;
import mayphoo.mpk.sfc.delegates.NewsItemDelegate;
import mayphoo.mpk.sfc.viewholders.NewsViewHolder;

/**
 * Created by User on 11/4/2017.
 */

public class NewsAdapter extends BaseRecyclerAdapter<NewsViewHolder, NewsVO> {

    private NewsItemDelegate mNewsItemDelegate;

    public NewsAdapter(Context context, NewsItemDelegate newsItemDelegate){
        super(context);
        mNewsItemDelegate = newsItemDelegate;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsItemView = mLayoutInflater.inflate(R.layout.view_item_news, parent, false); //third parameter is view object component directly attach to parent component
        return new NewsViewHolder(newsItemView, mNewsItemDelegate);
    }

}
