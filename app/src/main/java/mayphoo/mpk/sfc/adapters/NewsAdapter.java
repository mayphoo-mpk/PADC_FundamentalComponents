package mayphoo.mpk.sfc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mayphoo.mpk.sfc.R;
import mayphoo.mpk.sfc.viewholders.NewsViewHolder;

/**
 * Created by User on 11/4/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private LayoutInflater mLayoutInflater;

    public NewsAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsItemView = mLayoutInflater.inflate(R.layout.view_item_news, parent, false); //third parameter is view object component directly attach to parent component
        return new NewsViewHolder(newsItemView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
