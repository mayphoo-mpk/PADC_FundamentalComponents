package mayphoo.mpk.sfc.mvp.presenters;

import javax.inject.Inject;

import mayphoo.mpk.sfc.SFCNewsApp;
import mayphoo.mpk.sfc.data.models.NewsModel;
import mayphoo.mpk.sfc.mvp.views.AddNewsView;

/**
 * Created by User on 1/28/2018.
 */

public class AddNewsPresenter extends BasePresenter<AddNewsView> {

    @Inject
    NewsModel mNewsModel;

    @Override
    public void onCreate(AddNewsView view) {
        super.onCreate(view);

        //register in AppComponent
        SFCNewsApp sfcNewsApp = (SFCNewsApp) mView.getContext();
        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void onTapPublish(String photoPath, String newsContent) {
        mNewsModel.publishNews(photoPath, newsContent);
        /*mNewsModel.uploadFile(photoPath, new NewsModel.UploadFileCallback() {
            @Override
            public void onUploadSucceeded(String uploadedPaths) {
                mView.showUploadedNewsPhoto(uploadedPaths);
            }

            @Override
            public void onUploadFailed(String msg) {
                mView.showErrorMsg(msg);
            }
        });*/
    }

}
