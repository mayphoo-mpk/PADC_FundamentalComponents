package mayphoo.mpk.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.sfc.R;
import mayphoo.mpk.sfc.adapters.NewsImagesPagerAdapter;
import mayphoo.mpk.sfc.data.models.NewsModel;
import mayphoo.mpk.sfc.data.vo.NewsVO;
import mayphoo.mpk.sfc.persistence.MMNewsContract;

/**
 * Created by User on 11/11/2017.
 */

public class NewsDetailsActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

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

    private static final String IE_NEWS_ID = "IE_NEWS_ID";
    private static final int NEWS_DETAILS_LOADER_ID = 1002;

    private String mNewsId;

    NewsImagesPagerAdapter newsImagesPagerAdapter;

    /**
     * Create intent object to start NewsDetailsActivity
     * @param context
     * @param newsId : id for news object.
     * @return
     */
    public static Intent newIntent(Context context, String newsId){
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(IE_NEWS_ID, newsId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this, this);

        newsImagesPagerAdapter = new NewsImagesPagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagesPagerAdapter);

        mNewsId = getIntent().getStringExtra(IE_NEWS_ID);
        if(TextUtils.isEmpty(mNewsId)){
            throw new UnsupportedOperationException("newsId required for NewsDetailsActivity");
        } else {
            getSupportLoaderManager().initLoader(NEWS_DETAILS_LOADER_ID, null, this);
        }
    }

    private void bindData(NewsVO news){
        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        tvPublicationTitle.setText(news.getPublication().getTitle());
        tvPostedDate.setText(news.getPostedDate());
        tvNewsDetails.setText(news.getDetails());

        if(news.getImages().isEmpty()){

        } else {
            newsImagesPagerAdapter.setImages(news.getImages());
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                MMNewsContract.NewsEntry.CONTENT_URI,
                null,
                MMNewsContract.NewsEntry.COLUMN_NEWS_ID + " = ?",new String[]{mNewsId},
                null);
    }

    // convert cursor object to object format
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null && data.moveToFirst()){
            NewsVO news = NewsVO.parseFromCursor(getApplicationContext(), data);
            bindData(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
