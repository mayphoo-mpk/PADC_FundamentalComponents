package mayphoo.mpk.sfc.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import mayphoo.mpk.sfc.persistence.MMNewsContract;

/**
 * Created by User on 12/3/2017.
 */

public class PublicationVO {

    @SerializedName("publication-id")
    private String publicationId;

    @SerializedName("title")
    private String title;

    @SerializedName("logo")
    private String logo;

    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    //Change object format to content values format
    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID, publicationId);
        contentValues.put(MMNewsContract.PublicationEntry.COLUMN_TITLE, title);
        contentValues.put(MMNewsContract.PublicationEntry.COLUMN_LOGO, logo);
        return contentValues;
    }

    public static PublicationVO parseFromCursor(Cursor cursor) {
        PublicationVO publication = new PublicationVO();
        publication.publicationId = cursor.getString(cursor.getColumnIndex(MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID));
        publication.title = cursor.getString(cursor.getColumnIndex(MMNewsContract.PublicationEntry.COLUMN_TITLE));
        publication.logo = cursor.getString(cursor.getColumnIndex(MMNewsContract.PublicationEntry.COLUMN_LOGO));
        return publication;
    }

    public static PublicationVO dummyPublication() {
        PublicationVO publicationVO = new PublicationVO();
        publicationVO.publicationId = "pub719";
        publicationVO.title = "BBC Burmese";
        publicationVO.logo = "http://www.bbc.co.uk/news/special/2015/newsspec_11063/burmese_1024x576.png";
        return publicationVO;
    }
}
