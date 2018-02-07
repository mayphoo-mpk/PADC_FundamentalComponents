package mayphoo.mpk.sfc.viewitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.sfc.R;

/**
 * Created by User on 12/24/2017.
 */

public class NewsDetailsImageViewItem extends FrameLayout {

    @BindView(R.id.iv_details_image)
    ImageView ivNewsDetailsImage;

    public NewsDetailsImageViewItem(@NonNull Context context) {
        super(context);
    }

    public NewsDetailsImageViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsDetailsImageViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String imageUrl){
        Glide.with(ivNewsDetailsImage.getContext())
                .load(imageUrl)
                .into(ivNewsDetailsImage);
    }
}
