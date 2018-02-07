package mayphoo.mpk.sfc.mvp.views;

import android.content.Context;

/**
 * Created by User on 1/28/2018.
 */

public interface AddNewsView {

    Context getContext();

    void showUploadedNewsPhoto(String photoPath);

    void showErrorMsg(String msg);

}
