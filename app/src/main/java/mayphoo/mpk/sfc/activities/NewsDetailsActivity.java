package mayphoo.mpk.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.sfc.R;
import mayphoo.mpk.sfc.adapters.NewsImagesPagerAdapter;
import mayphoo.mpk.sfc.data.models.NewsModel;
import mayphoo.mpk.sfc.data.vo.NewsVO;

/**
 * Created by User on 11/11/2017.
 */

public class NewsDetailsActivity extends BaseActivity {

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.tv_pubication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_posted_date)
    TextView tvPostedDate;

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    private static final String tap_news_id = "tap_news_id";
    private NewsVO mNews;
    NewsImagesPagerAdapter newsImagesPagerAdapter;

    public static Intent newIntent(Context context, NewsVO news){
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(tap_news_id, news.getNewsId());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this, this);

        newsImagesPagerAdapter = new NewsImagesPagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagesPagerAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String newsId = bundle.getString(tap_news_id);
            mNews = NewsModel.getInstance().getNewsDetailsByNewId(newsId);
            bindData(mNews);
        }

    }

    private void bindData(NewsVO news){
        if(news.getImages() != null && news.getImages().size() > 0){
            newsImagesPagerAdapter.setImages(news.getImages());
        }

        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        tvPublicationTitle.setText(news.getPublication().getTitle());
        tvPostedDate.setText(news.getPostedDate());
        tvNewsDetails.setText(news.getDetails());

    }
}
