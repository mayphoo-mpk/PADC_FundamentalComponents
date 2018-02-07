package mayphoo.mpk.sfc.viewholders;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.sfc.R;
import mayphoo.mpk.sfc.data.vo.NewsVO;
import mayphoo.mpk.sfc.delegates.NewsItemDelegate;

/**
 * Created by User on 11/4/2017.
 */

public class NewsViewHolder extends BaseViewHolder<NewsVO> {

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.tv_pubication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_posted_date)
    TextView tvPostedDate;

    @BindView(R.id.tv_brief_news)
    TextView tvBriefNews;

    @BindView(R.id.iv_news_hero_image)
    ImageView ivNewsHeroImage;

    @BindView(R.id.tv_news_statistical_data)
    TextView tvNewsStatisticalData;

    private NewsItemDelegate mDelegate;
    private NewsVO mData;

    public NewsViewHolder(View itemView, NewsItemDelegate newsItemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mDelegate = newsItemDelegate;
    }

    @Override
    public void setData(NewsVO news) {
        mData = news;

        if(news.getPublication() != null){
            Glide.with(ivPublicationLogo.getContext())
                    .load(news.getPublication().getLogo())
                    .into(ivPublicationLogo);
            tvPublicationTitle.setText(news.getPublication().getTitle());
        }

        tvPostedDate.setText(news.getPostedDate());
        tvBriefNews.setText(news.getBrief());

        if(!news.getImages().isEmpty()){
            ivNewsHeroImage.setVisibility(View.VISIBLE);
            Glide.with(ivNewsHeroImage.getContext())
                    .load(news.getImages().get(0))
                    .into(ivNewsHeroImage);
        } else {
            ivNewsHeroImage.setVisibility(View.GONE);
        }

        int favoriteCount = news.getFavoriteActions() != null ?  news.getFavoriteActions().size() : 0;
        int likeCount = news.getCommentActions() != null ?  news.getCommentActions().size() : 0;
        int shareCount = news.getSentToActions() != null ?  news.getSentToActions().size() : 0;
        Log.d("news statistical data", "favorite count : " + favoriteCount +
        "like count: " + likeCount + "share count: " + shareCount);

        tvNewsStatisticalData.setText(favoriteCount + " Likes - "
                + likeCount + " Comments - Sent to "
                + shareCount + " people");

    }

    @Override
    public void onClick(View view) {
        mDelegate.onTapNews(mData);
    }
}
